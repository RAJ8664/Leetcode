# 3633. Maximize The Number Of Target Nodes After Connecting Trees I

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3633-maximize-the-number-of-target-nodes-after-connecting-trees-i){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i">3633. Maximize the Number of Target Nodes After Connecting Trees I</a></h2><h3>Medium</h3><hr><p>There exist two <strong>undirected </strong>trees with <code>n</code> and <code>m</code> nodes, with <strong>distinct</strong> labels in ranges <code>[0, n - 1]</code> and <code>[0, m - 1]</code>, respectively.</p>

<p>You are given two 2D integer arrays <code>edges1</code> and <code>edges2</code> of lengths <code>n - 1</code> and <code>m - 1</code>, respectively, where <code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the first tree and <code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the second tree. You are also given an integer <code>k</code>.</p>

<p>Node <code>u</code> is <strong>target</strong> to node <code>v</code> if the number of edges on the path from <code>u</code> to <code>v</code> is less than or equal to <code>k</code>. <strong>Note</strong> that a node is <em>always</em> <strong>target</strong> to itself.</p>

<p>Return an array of <code>n</code> integers <code>answer</code>, where <code>answer[i]</code> is the <strong>maximum</strong> possible number of nodes <strong>target</strong> to node <code>i</code> of the first tree if you have to connect one node from the first tree to another node in the second tree.</p>

<p><strong>Note</strong> that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,7,9,8,8]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, connect node 0 from the first tree to node 0 from the second tree.</li>
	<li>For <code>i = 1</code>, connect node 1 from the first tree to node 0 from the second tree.</li>
	<li>For <code>i = 2</code>, connect node 2 from the first tree to node 4 from the second tree.</li>
	<li>For <code>i = 3</code>, connect node 3 from the first tree to node 4 from the second tree.</li>
	<li>For <code>i = 4</code>, connect node 4 from the first tree to node 4 from the second tree.</li>
</ul>
<img alt="" src="https://assets.leetcode.com/uploads/2024/09/24/3982-1.png" style="width: 600px; height: 169px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,3,3,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>For every <code>i</code>, connect node <code>i</code> of the first tree with any node of the second tree.</p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/09/24/3928-2.png" style="height: 281px; width: 500px;" /></div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n, m &lt;= 1000</code></li>
	<li><code>edges1.length == n - 1</code></li>
	<li><code>edges2.length == m - 1</code></li>
	<li><code>edges1[i].length == edges2[i].length == 2</code></li>
	<li><code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; m</code></li>
	<li>The input is generated such that <code>edges1</code> and <code>edges2</code> represent valid trees.</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;

class Solution {
    private ArrayList<ArrayList<Integer>> adj1;
    private ArrayList<ArrayList<Integer>> adj2;
    private int depth[];
    private int res[];

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length, m = edges2.length;
        adj1 = new ArrayList<>();
        adj2 = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj1.add(new ArrayList<>());
        for (int i = 0; i <= m + 1; i++)
            adj2.add(new ArrayList<>());
        for (int i = 0; i < edges1.length; i++) {
            int u = edges1[i][0], v = edges1[i][1];
            adj1.get(u).add(v);
            adj1.get(v).add(u);
        }
        for (int i = 0; i < edges2.length; i++) {
            int u = edges2[i][0], v = edges2[i][1];
            adj2.get(u).add(v);
            adj2.get(v).add(u);
        }
        depth = new int[m + 2];
        res = new int[n + 1];
        int find2 = 0;
        for (int i = 0; i <= m; i++) {
            depth = new int[m + 2];
            find2 = Math.max(find2, find2(i, k - 1, -1));
        }
        for (int i = 0; i <= n; i++) {
            depth = new int[n + 2];
            res[i] = find1(i, k, -1) + find2;
        }
        return res;
    }

    private int find1(int u, int k, int par) {
        if (depth[u] > k)
            return 0;
        int res = 1;
        for (int v : adj1.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                res += find1(v, k, u);
            }
        }
        return res;
    }

    private int find2(int u, int k, int par) {
        if (depth[u] > k)
            return 0;
        int res = 1;
        for (int v : adj2.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                res += find2(v, k, u);
            }
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

