# 3809. Properties Graph

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/properties-graph/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3809-properties-graph){ .md-button }

---

<h2><a href="https://leetcode.com/problems/properties-graph">3809. Properties Graph</a></h2><h3>Medium</h3><hr><p>You are given a 2D integer array <code>properties</code> having dimensions <code>n x m</code> and an integer <code>k</code>.</p>

<p>Define a function <code>intersect(a, b)</code> that returns the <strong>number of distinct integers</strong> common to both arrays <code>a</code> and <code>b</code>.</p>

<p>Construct an <strong>undirected</strong> graph where each index <code>i</code> corresponds to <code>properties[i]</code>. There is an edge between node <code>i</code> and node <code>j</code> if and only if <code>intersect(properties[i], properties[j]) &gt;= k</code>, where <code>i</code> and <code>j</code> are in the range <code>[0, n - 1]</code> and <code>i != j</code>.</p>

<p>Return the number of <strong>connected components</strong> in the resulting graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The graph formed has 3 connected components:</p>

<p><img height="171" src="https://assets.leetcode.com/uploads/2025/02/27/image.png" width="279" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The graph formed has 1 connected component:</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/02/27/screenshot-from-2025-02-27-23-58-34.png" style="width: 219px; height: 171px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,1],[1,1]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>intersect(properties[0], properties[1]) = 1</code>, which is less than <code>k</code>. This means there is no edge between <code>properties[0]</code> and <code>properties[1]</code> in the graph.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == properties.length &lt;= 100</code></li>
	<li><code>1 &lt;= m == properties[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= properties[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= m</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class DSU {
        int parent[], size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void unite(int u, int v) {
            u = Leader(u); v = Leader(v);
            if (u == v)
                return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            size[u] += size[v];
            parent[v] = u;
        }
        public int Leader(int u) {
            if (u == parent[u]) 
                return u;
            return parent[u] = Leader(parent[u]);
        }
    }

    public int numberOfComponents(int[][] properties, int k) {
        DSU dsu = new DSU(properties.length + 1);
        for (int i = 0; i < properties.length; i++) {
            HashSet<Integer> curr = new HashSet<>();
            for (int ele : properties[i]) 
                curr.add(ele);
            for (int j = i + 1; j < properties.length; j++) {
                HashSet<Integer> newH = new HashSet<>();
                for (int ele : properties[j]) 
                    newH.add(ele);
                int count = 0;
                for (int ele : curr) 
                    if (newH.contains(ele)) 
                        count++;
                if (count >= k) 
                    dsu.unite(i, j); 
            }
        }        

        int res = 0;
        for (int i = 0; i < properties.length; i++) {
            if (dsu.Leader(i) == i) 
                res++;
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

