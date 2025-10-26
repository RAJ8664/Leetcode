# 3853. Minimum Weighted Subgraph With The Required Paths Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3853-minimum-weighted-subgraph-with-the-required-paths-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths-ii">3853. Minimum Weighted Subgraph With the Required Paths II</a></h2><h3>Hard</h3><hr><p>You are given an <strong>undirected weighted</strong> tree with <code data-end="51" data-start="48">n</code> nodes, numbered from <code data-end="75" data-start="72">0</code> to <code data-end="86" data-start="79">n - 1</code>. It is represented by a 2D integer array <code data-end="129" data-start="122">edges</code> of length <code data-end="147" data-start="140">n - 1</code>, where <code data-end="185" data-start="160">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code data-end="236" data-start="232">u<sub>i</sub></code> and <code data-end="245" data-start="241">v<sub>i</sub></code> with weight <code data-end="262" data-start="258">w<sub>i</sub></code>.​</p>

<p>Additionally, you are given a 2D integer array <code data-end="56" data-start="47">queries</code>, where <code data-end="105" data-start="69">queries[j] = [src1<sub>j</sub>, src2<sub>j</sub>, dest<sub>j</sub>]</code>.</p>

<p>Return an array <code data-end="24" data-start="16">answer</code> of length equal to <code data-end="60" data-start="44">queries.length</code>, where <code data-end="79" data-start="68">answer[j]</code> is the <strong>minimum total weight</strong> of a subtree such that it is possible to reach <code data-end="174" data-start="167">dest<sub>j</sub></code> from both <code data-end="192" data-start="185">src1<sub>j</sub></code> and <code data-end="204" data-start="197">src2<sub>j</sub></code> using edges in this subtree.</p>

<p>A <strong data-end="2287" data-start="2276">subtree</strong> here is any connected subset of nodes and edges of the original tree forming a valid tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]], queries = [[2,3,4],[0,2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[12,11]</span></p>

<p><strong>Explanation:</strong></p>

<p>The blue edges represent one of the subtrees that yield the optimal answer.</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/04/02/tree1-4.jpg" style="width: 531px; height: 322px;" /></p>

<ul>
	<li data-end="118" data-start="0">
	<p data-end="118" data-start="2"><code>answer[0]</code>: The total weight of the selected subtree that ensures a path from <code>src1 = 2</code> and <code>src2 = 3</code> to <code>dest = 4</code> is <code>3 + 5 + 4 = 12</code>.</p>
	</li>
	<li data-end="235" data-start="119">
	<p data-end="235" data-start="121"><code>answer[1]</code>: The total weight of the selected subtree that ensures a path from <code>src1 = 0</code> and <code>src2 = 2</code> to <code>dest = 5</code> is <code>2 + 3 + 6 = 11</code>.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,0,8],[0,2,7]], queries = [[0,1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[15]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/04/02/tree1-5.jpg" style="width: 270px; height: 80px;" /></p>

<ul>
	<li><code>answer[0]</code>: The total weight of the selected subtree that ensures a path from <code>src1 = 0</code> and <code>src2 = 1</code> to <code>dest = 2</code> is <code>8 + 7 = 15</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="36" data-start="20"><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li data-end="62" data-start="39"><code>edges.length == n - 1</code></li>
	<li data-end="87" data-start="65"><code>edges[i].length == 3</code></li>
	<li data-end="107" data-start="90"><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li data-end="127" data-start="110"><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li data-end="159" data-start="130"><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="186" data-start="162"><code>queries[j].length == 3</code></li>
	<li data-end="219" data-start="189"><code>0 &lt;= src1<sub>j</sub>, src2<sub>j</sub>, dest<sub>j</sub> &lt; n</code></li>
	<li><code>src1<sub>j</sub></code>, <code>src2<sub>j</sub></code>, and <code>dest<sub>j</sub></code> are pairwise distinct.</li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node, weight;
        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + ")";
        }
    }
    private ArrayList<ArrayList<Pair>> adj;
    private int dp[][];
    private int depth[];
    private int vis[];
    private int pref[];
    private int res[];
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0] + 1, v = edge[1] + 1, wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        dp = new int[n + 1][19];
        depth = new int[n + 1];
        vis = new int[n + 1];
        dfs(1, 0);
        pref = new int[n + 1];
        build_pref(n);
        res = new int[queries.length];
        System.out.println(Arrays.toString(pref));
        for (int i = 0; i < queries.length; i++) {
            int src1 = queries[i][0] + 1, src2 = queries[i][1] + 1, dest = queries[i][2] + 1;
            int lca1 = lca(src1, dest), lca2 = lca(src2, dest), lca3 = lca(src1, src2);
            int dist1 = pref[src1] + pref[dest] - 2 * pref[lca1];
            int dist2 = pref[src2] + pref[dest] - 2 * pref[lca2];
            int dist3 = pref[src1] + pref[src2] - 2 * pref[lca3];
            res[i] = (dist1 + dist2 + dist3) / 2;
        }
        return res;
    }
    private void build_pref(int n) {
        pref[1] = 0;
        vis = new int[n + 1];
        vis[1] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        while (q.size() > 0) {
            int curr_node = q.peek();
            int curr_dist = pref[curr_node];
            q.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                int child_weight = adj.get(curr_node).get(i).weight;
                if (vis[child_node] == 0) {
                    vis[child_node] = 1;
                    pref[child_node] = child_weight + curr_dist;
                    q.offer(child_node);
                } 
            }
        }
    }
    private int find_kth_parent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1) u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }
    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = find_kth_parent(v, diff);
        if (u == v) return u;
        for (int i = 18; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];
    }
    private void dfs(int u, int par) {
        vis[u] = 1;
        dp[u][0] = par;
        for (int i = 1; i < 19; i++) dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int i = 0; i < adj.get(u).size(); i++) {
            int v = adj.get(u).get(i).node;
            if (vis[v] == 0) {
                depth[v] = 1 + depth[u];
                dfs(v, u);                
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

