# 1248. Binary Tree Coloring Game

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/binary-tree-coloring-game/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1248-binary-tree-coloring-game){ .md-button }

---

<h2><a href="https://leetcode.com/problems/binary-tree-coloring-game">1248. Binary Tree Coloring Game</a></h2><h3>Medium</h3><hr><p>Two players play a turn based game on a binary tree. We are given the <code>root</code> of this binary tree, and the number of nodes <code>n</code> in the tree. <code>n</code> is odd, and each node has a distinct value from <code>1</code> to <code>n</code>.</p>

<p>Initially, the first player names a value <code>x</code> with <code>1 &lt;= x &lt;= n</code>, and the second player names a value <code>y</code> with <code>1 &lt;= y &lt;= n</code> and <code>y != x</code>. The first player colors the node with value <code>x</code> red, and the second player colors the node with value <code>y</code> blue.</p>

<p>Then, the players take turns starting with the first player. In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an <strong>uncolored</strong> neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)</p>

<p>If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass their turn, the game ends, and the winner is the player that colored more nodes.</p>

<p>You are the second player. If it is possible to choose such a <code>y</code> to ensure you win the game, return <code>true</code>. If it is not possible, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/08/01/1480-binary-tree-coloring-game.png" style="width: 500px; height: 310px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
<strong>Output:</strong> true
<strong>Explanation: </strong>The second player can choose the node with value 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3], n = 3, x = 1
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>1 &lt;= x &lt;= n &lt;= 100</code></li>
	<li><code>n</code> is odd.</li>
	<li>1 &lt;= Node.val &lt;= n</li>
	<li>All the values of the tree are <strong>unique</strong>.</li>
</ul>


---

## Solution

```java

/**
    Definition for a binary tree node.
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
    }
*/
class Solution {
    private ArrayList<ArrayList<Integer >> adj;
    private int id;
    private HashMap<TreeNode, Integer> getId;
    private HashMap<Integer, TreeNode> getNode;
    private int dp[][];
    private int depth[];
    private int subtree[];
    private int xthNode;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x1) {
        adj = new ArrayList<>();
        getId = new HashMap<>();
        getNode = new HashMap<>();
        subtree = new int[n + 1];
        for (int i = 0; i <= n + 10; i++)
            adj.add(new ArrayList<>());

        xthNode = -1;
        id = 1;
        BuildGraph(root, x1);
        
        dp = new int[n + 1][19];
        depth = new int[n + 1];

        dfs(1, 0);
       
        int x = xthNode;

        for (int i = 1; i <= n; i++) {
            //what will happen i choose this node as second player start point;
            if (i != x) {
                int lca = lca(i, x);
                if (lca == x || lca == i) {
                    int total = n;
                    int firstPlayer = 0, secondPlayer = 0;
                    if (depth[x] < depth[i]) {
                        //how many times can i jump upwards;
                        int times = (depth[i] - depth[x]) / 2;
                        int newI = findKthParent(i, times);
                        secondPlayer += subtree[newI];
                        firstPlayer += n - secondPlayer;
                        if (secondPlayer > firstPlayer) {
                            return true;
                        }
                    }
                    else {
                        int times = (depth[x] - depth[i]) / 2;
                        int newX = findKthParent(x, times);
                        firstPlayer += subtree[newX];
                        secondPlayer += n - firstPlayer;
                        if (secondPlayer > firstPlayer) 
                            return true;
                    }
                }
                else {
                    //they don't lie in same subtree;
                    int dist = depth[x] + depth[i] - 2 * depth[lca];
                    int firstPlayer = 0, secondPlayer = 0;
                    if (depth[x] > depth[i]) {
                        int totalNode = dist - 1;
                        int times = totalNode / 2;
                        if (totalNode % 2 != 0) times++;
                        int newX = findKthParent(x, times);
                        firstPlayer += subtree[newX];
                        secondPlayer += n - firstPlayer;
                        if (secondPlayer > firstPlayer)
                            return true;
                    }
                    else if (depth[i] < depth[x]) {
                        int totalNode = dist - 1;
                        int times = totalNode / 2;
                        int newI = findKthParent(i, times);
                        secondPlayer += subtree[newI];
                        firstPlayer += n - secondPlayer;
                        if (secondPlayer > firstPlayer) 
                            return true;
                    }
                    else {
                        //what if both of them have same depth;
                        int times = (dist - 1) / 2;
                        int newI = findKthParent(i, times);
                        secondPlayer += subtree[newI];
                        firstPlayer += n - secondPlayer;
                        if (secondPlayer > firstPlayer) 
                            return true;
                    }
                }
            }
        }
        return false;
    }

     private void BuildGraph(TreeNode root, int x) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (!getId.containsKey(q.peek())) {
                    getId.put(q.peek(), id);
                    getNode.put(id, q.peek());
                    id++;
                }
                if (q.peek().left != null) {
                    q.offer(q.peek().left);
                    getId.put(q.peek().left, id);
                    getNode.put(id, q.peek().left);
                    id++;

                    int u = getId.get(q.peek()), v = getId.get(q.peek().left);
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                }
                if (q.peek().right != null) {
                    q.offer(q.peek().right);
                    getId.put(q.peek().right, id);
                    getNode.put(id, q.peek().right);
                    id++;

                    int u = getId.get(q.peek()), v = getId.get(q.peek().right);
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                }
                if (q.peek().val == x) {
                    if (xthNode == -1) 
                        xthNode = getId.get(q.peek());
                }
                q.poll();
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = findKthParent(v, diff);
        if (u == v) 
            return u;
        for (int i = 18; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];
    }

    private int findKthParent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1) 
                u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }

    private void dfs(int u, int par) {
        subtree[u] = 1;
        dp[u][0] = par;
        for (int i = 1; i < 19; i++) 
            dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int v : adj.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
                subtree[u] += subtree[v];
            }     
        }
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

