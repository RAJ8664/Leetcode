# 2677. Cousins In Binary Tree Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/cousins-in-binary-tree-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2677-cousins-in-binary-tree-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/cousins-in-binary-tree-ii">2677. Cousins in Binary Tree II</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, replace the value of each node in the tree with the <strong>sum of all its cousins&#39; values</strong>.</p>

<p>Two nodes of a binary tree are <strong>cousins</strong> if they have the same depth with different parents.</p>

<p>Return <em>the </em><code>root</code><em> of the modified tree</em>.</p>

<p><strong>Note</strong> that the depth of a node is the number of edges in the path from the root node to it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/01/11/example11.png" style="width: 571px; height: 151px;" />
<pre>
<strong>Input:</strong> root = [5,4,9,1,10,null,7]
<strong>Output:</strong> [0,0,0,7,7,null,11]
<strong>Explanation:</strong> The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/01/11/diagram33.png" style="width: 481px; height: 91px;" />
<pre>
<strong>Input:</strong> root = [3,1,2]
<strong>Output:</strong> [0,0,0]
<strong>Explanation:</strong> The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>


---

## Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static class Pair {
        TreeNode current;
        int level;
        int val;
        TreeNode parent;
        int child;
        public Pair (TreeNode current, int level , int val, TreeNode parent, int child) {
            this.current = current;
            this.level = level;
            this.val = val;
            this.parent = parent;
            this.child = child;
        }
    }
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, root.val, null, -1));
        ArrayList<Integer> level_sum = new ArrayList<>();
        HashMap<TreeNode, Integer> map = new HashMap<>();
        while (q.size() > 0) {
            int len = q.size();
            int sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode curr = q.peek().current;
                if (q.peek().current.left != null) {
                    q.offer(new Pair(q.peek().current.left, q.peek().level + 1, q.peek().current.left.val, q.peek().current, 0));
                    if (q.peek().current.right != null) map.put(q.peek().current.left, q.peek().current.right.val);
                    else map.put(q.peek().current.left, 0);
                }
                if (q.peek().current.right != null) {
                    q.offer(new Pair(q.peek().current.right, q.peek().level + 1, q.peek().current.right.val, q.peek().current, 1));
                    if (q.peek().current.left != null) map.put(q.peek().current.right, q.peek().current.left.val);
                    else map.put(q.peek().current.right, 0);
                }
                sum += q.peek().current.val;
                q.poll();
            }
            level_sum.add(sum);
        }     
        int current_level = 0;
        Queue<TreeNode> new_q = new LinkedList<>();
        new_q.offer(root);
        while (new_q.size() > 0) {
            int len = new_q.size();
            for (int i = 0; i < len; i++) {
                if (new_q.peek().left != null) {
                    new_q.offer(new_q.peek().left);
                }
                if (new_q.peek().right != null) {
                    new_q.offer(new_q.peek().right);
                }
                if (current_level == 0) {
                    new_q.peek().val = 0;
                    new_q.poll();
                }
                if (current_level != 0) {
                    int sum = 0;
                    sum += map.get(new_q.peek());
                    sum += new_q.peek().val;
                    int current_level_sum = level_sum.get(current_level);
                    new_q.peek().val = current_level_sum - sum;
                    new_q.poll();
                }
            }
            current_level++;
        }
        return root;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

