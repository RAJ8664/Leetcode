# 2545. Height Of Binary Tree After Subtree Removal Queries

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2545-height-of-binary-tree-after-subtree-removal-queries){ .md-button }

---

<h2><a href="https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries">2545. Height of Binary Tree After Subtree Removal Queries</a></h2><h3>Hard</h3><hr><p>You are given the <code>root</code> of a <strong>binary tree</strong> with <code>n</code> nodes. Each node is assigned a unique value from <code>1</code> to <code>n</code>. You are also given an array <code>queries</code> of size <code>m</code>.</p>

<p>You have to perform <code>m</code> <strong>independent</strong> queries on the tree where in the <code>i<sup>th</sup></code> query you do the following:</p>

<ul>
	<li><strong>Remove</strong> the subtree rooted at the node with the value <code>queries[i]</code> from the tree. It is <strong>guaranteed</strong> that <code>queries[i]</code> will <strong>not</strong> be equal to the value of the root.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of size </em><code>m</code><em> where </em><code>answer[i]</code><em> is the height of the tree after performing the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>The queries are independent, so the tree returns to its <strong>initial</strong> state after each query.</li>
	<li>The height of a tree is the <strong>number of edges in the longest simple path</strong> from the root to some node in the tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/09/07/binaryytreeedrawio-1.png" style="width: 495px; height: 281px;" />
<pre>
<strong>Input:</strong> root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
<strong>Output:</strong> [2]
<strong>Explanation:</strong> The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -&gt; 3 -&gt; 2).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/09/07/binaryytreeedrawio-2.png" style="width: 301px; height: 284px;" />
<pre>
<strong>Input:</strong> root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
<strong>Output:</strong> [3,2,3,2]
<strong>Explanation:</strong> We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -&gt; 8 -&gt; 2 -&gt; 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -&gt; 8 -&gt; 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -&gt; 8 -&gt; 2 -&gt; 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -&gt; 9 -&gt; 3).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= min(n, 10<sup>4</sup>)</code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
	<li><code>queries[i] != root.val</code></li>
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
    private int depth[];
    private ArrayList<Integer> tour;
    private int first[];
    private int last[];
    public int[] treeQueries(TreeNode root, int[] queries) {
        Build_Graph(root);
        depth = new int[(int)(1e5 + 1)];
        tour = new ArrayList<>();
        first = new int[(int)(1e5 + 1)];
        last = new int[(int)(1e5 + 1)];
        Arrays.fill(first, -1); Arrays.fill(last, -1);

        Euler_Dfs(root.val, 0);
        for (int i = 0; i < tour.size(); i++) {
            if (first[tour.get(i)] == -1) first[tour.get(i)] = i;
            else last[tour.get(i)] = i;
        }

        int maxi_pref[] = new int[tour.size() + 1];
        int maxi_suff[] = new int[tour.size() + 1];
        int maxi = 0;
        for (int i = 0; i < tour.size(); i++) {
            int current = depth[tour.get(i)];
            maxi = Math.max(maxi, current);
            maxi_pref[i] = maxi;
        }

        maxi = 0;
        for (int i = tour.size() - 1; i >= 0; i--) {
            int current = depth[tour.get(i)];
            maxi = Math.max(maxi, current);
            maxi_suff[i] = maxi;
        }

        int res[] = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int node = queries[i];
            int left = first[node];
            int right = last[node];
            int current_maxi = 0;
            if (left - 1 >= 0) current_maxi = Math.max(current_maxi, maxi_pref[left - 1]);
            if (right + 1 < tour.size()) current_maxi = Math.max(current_maxi, maxi_suff[right + 1]);
            res[i] = current_maxi;
        }
        return res;
    }

    private void Euler_Dfs(int u , int par) {
        tour.add(u);
        for (int v : adj.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                Euler_Dfs(v, u);
            }
        }
        tour.add(u);
    }

    private void Build_Graph(TreeNode root) {
        adj = new ArrayList<>();
        for (int i = 0; i <= (int)(1e5 + 1); i++) adj.add(new ArrayList<>());
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int u = q.peek().val;
                if (q.peek().left != null) {
                    q.offer(q.peek().left);
                    int v = q.peek().left.val;
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                }
                if (q.peek().right != null) {
                    q.offer(q.peek().right);
                    int v = q.peek().right.val;
                    adj.get(u).add(v);
                    adj.get(v).add(u);
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

