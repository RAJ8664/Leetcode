# 3439. Find Minimum Diameter After Merging Two Trees

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3439-find-minimum-diameter-after-merging-two-trees){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees">3439. Find Minimum Diameter After Merging Two Trees</a></h2><h3>Hard</h3><hr><p>There exist two <strong>undirected </strong>trees with <code>n</code> and <code>m</code> nodes, numbered from <code>0</code> to <code>n - 1</code> and from <code>0</code> to <code>m - 1</code>, respectively. You are given two 2D integer arrays <code>edges1</code> and <code>edges2</code> of lengths <code>n - 1</code> and <code>m - 1</code>, respectively, where <code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the first tree and <code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the second tree.</p>

<p>You must connect one node from the first tree with another node from the second tree with an edge.</p>

<p>Return the <strong>minimum </strong>possible <strong>diameter </strong>of the resulting tree.</p>

<p>The <strong>diameter</strong> of a tree is the length of the <em>longest</em> path between any two nodes in the tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong><img alt="" src="https://assets.leetcode.com/uploads/2024/04/22/example11-transformed.png" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/04/22/example211.png" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>edges1.length == n - 1</code></li>
	<li><code>edges2.length == m - 1</code></li>
	<li><code>edges1[i].length == edges2[i].length == 2</code></li>
	<li><code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; m</code></li>
	<li>The input is generated such that <code>edges1</code> and <code>edges2</code> represent valid trees.</li>
</ul>


---

## Solution

```cpp
class Solution {
public:
    //why the same solution in java is giving tle; ????
    int minimumDiameterAfterMerge(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        vector<vector<int>> adj1(100007);
        vector<vector<int>> adj2(100007);
        for (auto& edge : edges1) {
            int u = edge[0], v = edge[1];
            adj1[u].push_back(v);
            adj1[v].push_back(u);
        }
        for (auto& edge : edges2) {
            int u = edge[0], v = edge[1];
            adj2[u].push_back(v);
            adj2[v].push_back(u);
        }
        if (edges1.empty() && edges2.empty()) return 1;
        if (edges1.empty()) {
            int d2 = findDiameter(adj2, edges2.size() + 1);
            if (edges2.size() == 1) return d2 + 1;
            else return d2;
        }
        if (edges2.empty()) {
            int d1 = findDiameter(adj1, edges1.size() + 1);
            if (edges1.size() == 1) return d1 + 1;
            else return d1;
        }
        int d1 = findDiameter(adj1, edges1.size() + 1);
        int d2 = findDiameter(adj2, edges2.size() + 1);
        int res = (d1 / 2) + (d2 / 2) + 1;
        if (d1 % 2 == 1) res++;
        if (d2 % 2 == 1) res++;
        res = max(res, d1);
        res = max(res, d2);
        return res;
    }
    int findDiameter(vector<vector<int>>& adj, int len) {
        int n = len;
        vector<int> depth(n + 1, 0);
        dfs(0, -1, adj, depth);
        int maxi = 0, node = -1;
        for (int i = 0; i <= n; ++i) {
            if (depth[i] > maxi) {
                maxi = depth[i];
                node = i;
            }
        }
        fill(depth.begin(), depth.end(), 0);
        dfs(node, -1, adj, depth);
        maxi = 0; node = - 1;
        for (int i = 0; i <= n; ++i) {
            if (depth[i] > maxi) {
                maxi = depth[i];
                node = i;
            }
        }
        return maxi;
    }
    void dfs(int u, int par, vector<vector<int>>& adj, vector<int>& depth) {
        for (int v : adj[u]) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u, adj, depth);
            }
        }
    }
};
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

