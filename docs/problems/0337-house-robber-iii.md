# 337. House Robber Iii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/house-robber-iii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0337-house-robber-iii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/house-robber-iii">337. House Robber III</a></h2><h3>Medium</h3><hr><p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called <code>root</code>.</p>

<p>Besides the <code>root</code>, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if <strong>two directly-linked houses were broken into on the same night</strong>.</p>

<p>Given the <code>root</code> of the binary tree, return <em>the maximum amount of money the thief can rob <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob1-tree.jpg" style="width: 277px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,2,3,null,3,null,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob2-tree.jpg" style="width: 357px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,3,null,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 4 + 5 = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
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
    private ArrayList<ArrayList<Integer>> adj;
    private int cost[];
    private int dp1[];
    private int dp2[];
    public int rob(TreeNode root) {
        build_tree(root);
        //dp1 = taking value at node i;
        //dp2 = not taking value at node i;
        dp1 = new int[(int)(1e4) + 1];
        dp2 = new int[(int)(1e4) + 1];
        dfs(1, -1);
        return Math.max(dp1[1] , dp2[1]);
    }
    private void dfs(int u , int par) {
        if (adj.get(u).size() == 1 && u != 1) {
            dp1[u] = cost[u];
            dp2[u] = 0;
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u);
                dp2[u] += Math.max(dp1[v] , dp2[v]);
                dp1[u] += dp2[v];
            }
        }
        dp1[u] += cost[u];
    }
    private void build_tree(TreeNode root) {
        adj = new ArrayList<>();
        cost = new int[(int)(1e5) + 1];
        HashMap<TreeNode, Integer> map1 = new HashMap<>();
        for (int i = 0; i <= (int)(1e4 + 1); i++) adj.add(new ArrayList<>());
        int id = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (!map1.containsKey(q.peek())) {
                    map1.put(q.peek(), id);
                    id++;
                } 
                int u = map1.get(q.peek());
                cost[u] = q.peek().val;
                if (q.peek().left != null) {
                    map1.put(q.peek().left, id);
                    int v = id;
                    cost[v] = q.peek().left.val;
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                    id++;
                    q.offer(q.peek().left);
                }
                if (q.peek().right != null) {
                    map1.put(q.peek().right, id);
                    int v = id;
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                    cost[v] = q.peek().right.val;
                    id++;
                    q.offer(q.peek().right);
                }
                q.poll();
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

