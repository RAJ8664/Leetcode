# 2531. Create Components With Same Value

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/create-components-with-same-value/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2531-create-components-with-same-value){ .md-button }

---

<h2><a href="https://leetcode.com/problems/create-components-with-same-value">2531. Create Components With Same Value</a></h2><h3>Hard</h3><hr><p>There is an undirected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code><font face="monospace">nums</font></code> of length <code>n</code> where <code>nums[i]</code> represents the value of the <code>i<sup>th</sup></code> node. You are also given a 2D integer array <code>edges</code> of length <code>n - 1</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are allowed to <strong>delete</strong> some edges, splitting the tree into multiple connected components. Let the <strong>value</strong> of a component be the sum of <strong>all</strong> <code>nums[i]</code> for which node <code>i</code> is in the component.</p>

<p>Return<em> the <strong>maximum</strong> number of edges you can delete, such that every connected component in the tree has the same value.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/08/26/diagramdrawio.png" style="width: 441px; height: 351px;" />
<pre>
<strong>Input:</strong> nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]] 
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The above figure shows how we can delete the edges [0,1] and [3,4]. The created components are nodes [0], [1,2,3] and [4]. The sum of the values in each component equals 6. It can be proven that no better deletion exists, so the answer is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2], edges = []
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no edges to be deleted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int count;
    private int val[];
    private int dp[];
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        val = new int[n];
        dp = new int[n + 1];
        int start = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            sum += val[i];
            start = Math.max(start, val[i]);
        }
        
        count = 0;
        int maxi = 0;

        ArrayList<Integer> divs = new ArrayList<>();
        divs = getDivs(sum);

        for (int x = 0; x < divs.size(); x++) {
            int i = divs.get(x);
            dp = new int[n + 1];
            dfs(0, -1, i);
            if (dp[0] == 0) {
                maxi = Math.max(maxi, count - 1);
            }
            count = 0;
        }
        return maxi;
    }
    private void dfs(int u, int par, int req) {
        if (adj.get(u).size() == 1 && u != 0) {
            if (val[u] == req) {
                dp[u] = 0;
                count++;
            }
            else if (val[u] > req) {
                dp[u] = (int)(1e3);
            }
            else dp[u] = val[u];
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u, req);
            }
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dp[u] += dp[v];
            }
        }
        dp[u] += val[u];
        if (dp[u] == req) {
            count++;
            dp[u] = 0;
        }
        else if (dp[u] > req) {
            dp[u] = (int)(1e3);
        }
    }
    private ArrayList<Integer> getDivs(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (n / i != i) {
                    res.add(n / i);
                } 
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

