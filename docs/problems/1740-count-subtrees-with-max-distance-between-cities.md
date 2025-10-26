# 1740. Count Subtrees With Max Distance Between Cities

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1740-count-subtrees-with-max-distance-between-cities){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities">1740. Count Subtrees With Max Distance Between Cities</a></h2><h3>Hard</h3><hr><p>There are <code>n</code> cities numbered from <code>1</code> to <code>n</code>. You are given an array <code>edges</code> of size <code>n-1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> represents a bidirectional edge between cities <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>. There exists a unique path between each pair of cities. In other words, the cities form a <strong>tree</strong>.</p>

<p>A <strong>subtree</strong> is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.</p>

<p>For each <code>d</code> from <code>1</code> to <code>n-1</code>, find the number of subtrees in which the <strong>maximum distance</strong> between any two cities in the subtree is equal to <code>d</code>.</p>

<p>Return <em>an array of size</em> <code>n-1</code> <em>where the </em><code>d<sup>th</sup></code><em> </em><em>element <strong>(1-indexed)</strong> is the number of subtrees in which the <strong>maximum distance</strong> between any two cities is equal to </em><code>d</code>.</p>

<p><strong>Notice</strong>&nbsp;that&nbsp;the <strong>distance</strong> between the two cities is the number of edges in the path between them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/p1.png" style="width: 161px; height: 181px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[1,2],[2,3],[2,4]]
<strong>Output:</strong> [3,4,0]
<strong>Explanation:
</strong>The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
No subtree has two nodes where the max distance between them is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, edges = [[1,2]]
<strong>Output:</strong> [1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[1,2],[2,3]]
<strong>Output:</strong> [2,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>edges.length == n-1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li>All pairs <code>(u<sub>i</sub>, v<sub>i</sub>)</code> are distinct.</li>
</ul>

---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int arr[];
    private ArrayList<ArrayList<Integer>> subsets;
    private int dp[][];
    private int depth[];

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i + 1;
        subsets = new ArrayList<>();

        getSubsets(0, arr, new ArrayList<>());

        dp = new int[n + 1][19];
        depth = new int[n + 1];
        dfs(1, 0);

        int res[] = new int[n - 1];
        for (ArrayList<Integer> curr : subsets) {
            int maxi = 0, count = 0;
            for (int i = 0; i < curr.size(); i++) {
                for (int j = i + 1; j < curr.size(); j++) {
                    int u = curr.get(i), v = curr.get(j);
                    if (adj.get(u).contains(v)) count++;
                    int dist = depth[u] + depth[v] - 2 * depth[lca(u, v)];
                    maxi = Math.max(maxi, dist);
                }
            }
            if (count == curr.size() - 1 && maxi > 0) res[maxi - 1]++;
        }
        return res;
    }

    private void dfs(int u, int par) {
        dp[u][0] = par;
        for (int i = 1; i < 19; i++)
            dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int v : adj.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u =  v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = kthParent(v, diff);
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
    
    private int kthParent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1) 
                u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }

    private void getSubsets(int ind, int arr[], ArrayList<Integer> current) {
        if (ind >= arr.length) {
            if (current.size() <= 1) return;
            subsets.add(new ArrayList<>(current));
            return;
        }

        current.add(arr[ind]);
        getSubsets(ind + 1, arr, current);
        current.remove(current.size() - 1);
        getSubsets(ind + 1, arr, current);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

