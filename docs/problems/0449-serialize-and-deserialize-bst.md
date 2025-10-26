# 449. Serialize And Deserialize Bst

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/serialize-and-deserialize-bst/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0449-serialize-and-deserialize-bst){ .md-button }

---

<h2><a href="https://leetcode.com/problems/serialize-and-deserialize-bst">449. Serialize and Deserialize BST</a></h2><h3>Medium</h3><hr><p>Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize a <b>binary search tree</b>. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.</p>

<p><b>The encoded string should be as compact as possible.</b></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> [2,1,3]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The input tree is <strong>guaranteed</strong> to be a binary search tree.</li>
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

