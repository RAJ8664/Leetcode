# 297. Serialize And Deserialize Binary Tree

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0297-serialize-and-deserialize-binary-tree){ .md-button }

---

<h2><a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree">297. Serialize and Deserialize Binary Tree</a></h2><h3>Hard</h3><hr><p>Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.</p>

<p><strong>Clarification:</strong> The input/output format is the same as <a href="https://support.leetcode.com/hc/en-us/articles/360011883654-What-does-1-null-2-3-mean-in-binary-tree-representation-" target="_blank">how LeetCode serializes a binary tree</a>. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,null,4,5]
<strong>Output:</strong> [1,2,3,null,null,4,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
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
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        String res = "";
        res += root.val;
        res += ":";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size () > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (q.peek().left != null) {
                    q.offer(q.peek().left);
                    res += q.peek().left.val;
                    res += ":";
                }
                else {
                    res += "-100000";
                    res += ":";
                }
                if (q.peek().right != null) {
                    q.offer(q.peek().right);
                    res += q.peek().right.val;
                    res += ":";
                }
                else {
                    res += "-100000";
                    res += ":";
                }
                q.poll();
            }
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        ArrayList<Integer> res = new ArrayList<>();
        String current = "";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ':') {
                int x = Integer.parseInt(current);
                current = "";
                res.add(x);
            }
            else current += data.charAt(i);
        }
        if (res.size() == 0) return null;
        TreeNode root = new TreeNode(res.get(0));
        int current_ind = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            TreeNode current_node = q.poll();
            int left = -100000, right = -100000;
            if (current_ind < res.size()) left = res.get(current_ind++);
            if (current_ind < res.size()) right = res.get(current_ind++);
            if (left == -100000) current_node.left = null;
            else {
                current_node.left = new TreeNode(left);
                q.offer(current_node.left);
            }
            if (right == -100000) current_node.right = null;
            else {
                current_node.right = new TreeNode(right);
                q.offer(current_node.right);
            }
        }    
        return root;   
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

