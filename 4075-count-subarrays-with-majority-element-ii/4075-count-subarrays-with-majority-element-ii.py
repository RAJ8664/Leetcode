class Solution:
    class RedBlackNode:
        BLACK = 0
        RED = 1

        def __init__(self, key=None):
            self.key = key
            self.parent = None
            self.left = None
            self.right = None
            self.numLeft = 0
            self.numRight = 0
            self.color = Solution.RedBlackNode.BLACK

    class RedBlackTree:
        def __init__(self):
            self.nil = Solution.RedBlackNode()
            self.root = self.nil
            self.root.left = self.nil
            self.root.right = self.nil
            self.root.parent = self.nil

        def isNil(self, node):
            return node == self.nil

        def leftRotateFixup(self, x):
            if self.isNil(x.left) and self.isNil(x.right.left):
                x.numLeft = 0
                x.numRight = 0
                x.right.numLeft = 1
            elif self.isNil(x.left) and not self.isNil(x.right.left):
                x.numLeft = 0
                x.numRight = 1 + x.right.left.numLeft + x.right.left.numRight
                x.right.numLeft = (
                    2 + x.right.left.numLeft + x.right.left.numRight
                )
            elif not self.isNil(x.left) and self.isNil(x.right.left):
                x.numRight = 0
                x.right.numLeft = 2 + x.left.numLeft + x.left.numRight
            else:
                x.numRight = 1 + x.right.left.numLeft + x.right.left.numRight
                x.right.numLeft = (
                    3
                    + x.left.numLeft
                    + x.left.numRight
                    + x.right.left.numLeft
                    + x.right.left.numRight
                )

        def leftRotate(self, x):
            self.leftRotateFixup(x)
            y = x.right
            x.right = y.left
            if not self.isNil(y.left):
                y.left.parent = x
            y.parent = x.parent
            if self.isNil(x.parent):
                self.root = y
            elif x.parent.left == x:
                x.parent.left = y
            else:
                x.parent.right = y
            y.left = x
            x.parent = y

        def rightRotateFixup(self, y):
            if self.isNil(y.right) and self.isNil(y.left.right):
                y.numRight = 0
                y.numLeft = 0
                y.left.numRight = 1
            elif self.isNil(y.right) and not self.isNil(y.left.right):
                y.numRight = 0
                y.numLeft = 1 + y.left.right.numRight + y.left.right.numLeft
                y.left.numRight = (
                    2 + y.left.right.numRight + y.left.right.numLeft
                )
            elif not self.isNil(y.right) and self.isNil(y.left.right):
                y.numLeft = 0
                y.left.numRight = 2 + y.right.numRight + y.right.numLeft
            else:
                y.numLeft = 1 + y.left.right.numRight + y.left.right.numLeft
                y.left.numRight = (
                    3
                    + y.right.numRight
                    + y.right.numLeft
                    + y.left.right.numRight
                    + y.left.right.numLeft
                )

        def rightRotate(self, y):
            self.rightRotateFixup(y)
            x = y.left
            y.left = x.right
            if not self.isNil(x.right):
                x.right.parent = y
            x.parent = y.parent
            if self.isNil(y.parent):
                self.root = x
            elif y.parent.right == y:
                y.parent.right = x
            else:
                y.parent.left = x
            x.right = y
            y.parent = x

        def insert(self, key):
            self._insert(Solution.RedBlackNode(key))

        def _insert(self, z):
            y = self.nil
            x = self.root

            while not self.isNil(x):
                y = x
                if z.key < x.key:
                    x.numLeft += 1
                    x = x.left
                else:
                    x.numRight += 1
                    x = x.right

            z.parent = y

            if self.isNil(y):
                self.root = z
            elif z.key < y.key:
                y.left = z
            else:
                y.right = z

            z.left = self.nil
            z.right = self.nil
            z.color = Solution.RedBlackNode.RED

            self.insertFixup(z)

        def insertFixup(self, z):
            while z.parent.color == Solution.RedBlackNode.RED:
                if z.parent == z.parent.parent.left:
                    y = z.parent.parent.right
                    if y.color == Solution.RedBlackNode.RED:
                        z.parent.color = Solution.RedBlackNode.BLACK
                        y.color = Solution.RedBlackNode.BLACK
                        z.parent.parent.color = Solution.RedBlackNode.RED
                        z = z.parent.parent
                    elif z == z.parent.right:
                        z = z.parent
                        self.leftRotate(z)
                    else:
                        z.parent.color = Solution.RedBlackNode.BLACK
                        z.parent.parent.color = Solution.RedBlackNode.RED
                        self.rightRotate(z.parent.parent)
                else:
                    y = z.parent.parent.left
                    if y.color == Solution.RedBlackNode.RED:
                        z.parent.color = Solution.RedBlackNode.BLACK
                        y.color = Solution.RedBlackNode.BLACK
                        z.parent.parent.color = Solution.RedBlackNode.RED
                        z = z.parent.parent
                    elif z == z.parent.left:
                        z = z.parent
                        self.rightRotate(z)
                    else:
                        z.parent.color = Solution.RedBlackNode.BLACK
                        z.parent.parent.color = Solution.RedBlackNode.RED
                        self.leftRotate(z.parent.parent)

            self.root.color = Solution.RedBlackNode.BLACK

        def treeMinimum(self, node):
            while not self.isNil(node.left):
                node = node.left
            return node

        def treeSuccessor(self, x):
            if not self.isNil(x.left):
                return self.treeMinimum(x.right)
            y = x.parent
            while not self.isNil(y) and x == y.right:
                x = y
                y = y.parent
            return y

        def search(self, key):
            current = self.root
            while not self.isNil(current):
                if current.key == key:
                    return current
                elif current.key < key:
                    current = current.right
                else:
                    current = current.left
            return None

        def fixNodeData(self, x, y):
            if self.isNil(x):
                current = y.parent
                track = y
            else:
                current = x.parent
                track = x

            while not self.isNil(current):
                if y.key != current.key:
                    if y.key > current.key:
                        current.numRight -= 1
                    elif y.key < current.key:
                        current.numLeft -= 1
                else:
                    if self.isNil(current.left):
                        current.numLeft -= 1
                    elif self.isNil(current.right):
                        current.numRight -= 1
                    elif track == current.right:
                        current.numRight -= 1
                    elif track == current.left:
                        current.numLeft -= 1
                track = current
                current = current.parent

        def remove(self, node):
            z = self.search(node.key)
            if self.isNil(z.left) or self.isNil(z.right):
                y = z
            else:
                y = self.treeSuccessor(z)

            if not self.isNil(y.left):
                x = y.left
            else:
                x = y.right

            x.parent = y.parent

            if self.isNil(y.parent):
                self.root = x
            elif y.parent.left == y:
                y.parent.left = x
            else:
                y.parent.right = x

            if y != z:
                z.key = y.key

            self.fixNodeData(x, y)

            if y.color == Solution.RedBlackNode.BLACK:
                self.removeFixup(x)

        def removeFixup(self, x):
            while x != self.root and x.color == Solution.RedBlackNode.BLACK:
                if x == x.parent.left:
                    w = x.parent.right
                    if w.color == Solution.RedBlackNode.RED:
                        w.color = Solution.RedBlackNode.BLACK
                        x.parent.color = Solution.RedBlackNode.RED
                        self.leftRotate(x.parent)
                        w = x.parent.right

                    if (
                        w.left.color == Solution.RedBlackNode.BLACK
                        and w.right.color == Solution.RedBlackNode.BLACK
                    ):
                        w.color = Solution.RedBlackNode.RED
                        x = x.parent
                    else:
                        if w.right.color == Solution.RedBlackNode.BLACK:
                            w.left.color = Solution.RedBlackNode.BLACK
                            w.color = Solution.RedBlackNode.RED
                            self.rightRotate(w)
                            w = x.parent.right

                        w.color = x.parent.color
                        x.parent.color = Solution.RedBlackNode.BLACK
                        w.right.color = Solution.RedBlackNode.BLACK
                        self.leftRotate(x.parent)
                        x = self.root
                else:
                    w = x.parent.left
                    if w.color == Solution.RedBlackNode.RED:
                        w.color = Solution.RedBlackNode.BLACK
                        x.parent.color = Solution.RedBlackNode.RED
                        self.rightRotate(x.parent)
                        w = x.parent.left

                    if (
                        w.right.color == Solution.RedBlackNode.BLACK
                        and w.left.color == Solution.RedBlackNode.BLACK
                    ):
                        w.color = Solution.RedBlackNode.RED
                        x = x.parent
                    else:
                        if w.left.color == Solution.RedBlackNode.BLACK:
                            w.right.color = Solution.RedBlackNode.BLACK
                            w.color = Solution.RedBlackNode.RED
                            self.leftRotate(w)
                            w = x.parent.left

                        w.color = x.parent.color
                        x.parent.color = Solution.RedBlackNode.BLACK
                        w.left.color = Solution.RedBlackNode.BLACK
                        self.rightRotate(x.parent)
                        x = self.root

            x.color = Solution.RedBlackNode.BLACK

        def findNumGreater(self, node, key):
            if self.isNil(node):
                return 0
            elif key < node.key:
                return (
                    1
                    + node.numRight
                    + self.findNumGreater(node.left, key)
                )
            return self.findNumGreater(node.right, key)

        def findNumSmaller(self, node, key):
            if self.isNil(node):
                return 0
            elif key <= node.key:
                return self.findNumSmaller(node.left, key)
            else:
                return (
                    1
                    + node.numLeft
                    + self.findNumSmaller(node.right, key)
                )

        def numGreater(self, key):
            return self.findNumGreater(self.root, key)

        def numSmaller(self, key):
            return self.findNumSmaller(self.root, key)

        def getGreaterThan(self, key, maxReturned):
            res = []

            def dfs(node):
                if self.isNil(node):
                    return
                if node.key > key:
                    dfs(node.left)
                    res.append(node.key)
                    dfs(node.right)
                else:
                    dfs(node.right)

            dfs(self.root)
            return res[:maxReturned]

        def size(self):
            return self.root.numLeft + self.root.numRight + 1

    def countMajoritySubarrays(self, arr, target):
        n = len(arr)

        temp = [1 if x == target else -1 for x in arr]

        pref = [0] * n
        pref[0] = temp[0]
        for i in range(1, n):
            pref[i] = pref[i - 1] + temp[i]

        rb = self.RedBlackTree()

        ans = 0
        for x in pref:
            if x > 0:
                ans += 1
            ans += rb.numSmaller(x)
            rb.insert(x)

        return ans
        