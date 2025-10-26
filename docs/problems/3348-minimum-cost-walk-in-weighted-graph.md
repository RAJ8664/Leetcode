# 3348. Minimum Cost Walk In Weighted Graph

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3348-minimum-cost-walk-in-weighted-graph){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph">3348. Minimum Cost Walk in Weighted Graph</a></h2><h3>Hard</h3><hr><p>There is an undirected weighted graph with <code>n</code> vertices labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given the integer <code>n</code> and an array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between vertices <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with a weight of <code>w<sub>i</sub></code>.</p>

<p>A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It&#39;s important to note that a walk may visit the same edge or vertex more than once.</p>

<p>The <strong>cost</strong> of a walk starting at node <code>u</code> and ending at node <code>v</code> is defined as the bitwise <code>AND</code> of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is <code>w<sub>0</sub>, w<sub>1</sub>, w<sub>2</sub>, ..., w<sub>k</sub></code>, then the cost is calculated as <code>w<sub>0</sub> &amp; w<sub>1</sub> &amp; w<sub>2</sub> &amp; ... &amp; w<sub>k</sub></code>, where <code>&amp;</code> denotes the bitwise <code>AND</code> operator.</p>

<p>You are also given a 2D array <code>query</code>, where <code>query[i] = [s<sub>i</sub>, t<sub>i</sub>]</code>. For each query, you need to find the minimum cost of the walk starting at vertex <code>s<sub>i</sub></code> and ending at vertex <code>t<sub>i</sub></code>. If there exists no such walk, the answer is <code>-1</code>.</p>

<p>Return <em>the array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> denotes the <strong>minimum</strong> cost of a walk for query </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,-1]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/01/31/q4_example1-1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 351px; height: 141px;" />
<p>To achieve the cost of 1 in the first query, we need to move on the following edges: <code>0-&gt;1</code> (weight 7), <code>1-&gt;2</code> (weight 1), <code>2-&gt;1</code> (weight 1), <code>1-&gt;3</code> (weight 7).</p>

<p>In the second query, there is no walk between nodes 3 and 4, so the answer is -1.</p>

<p><strong class="example">Example 2:</strong></p>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/01/31/q4_example2e.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 211px; height: 181px;" />
<p>To achieve the cost of 0 in the first query, we need to move on the following edges: <code>1-&gt;2</code> (weight 1), <code>2-&gt;1</code> (weight 6), <code>1-&gt;2</code> (weight 1).</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= query.length &lt;= 10<sup>5</sup></code></li>
	<li><code>query[i].length == 2</code></li>
	<li><code>0 &lt;= s<sub>i</sub>, t<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>s<sub>i</sub> !=&nbsp;t<sub>i</sub></code></li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Pair>> adj;
    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> nodes;
    private int vis[];
    static class Pair {
        int node;
        int distance;
        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        @Override
        public String toString() {
            return "(" + node + " " + distance + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.distance, second.distance);
        }
    }
    static class DSU {
        private int parent[];
        private int size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void merge(int u , int v) {
            u = Leader(u);
            v = Leader(v);
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent[v] = u;
            size[u] += size[v];
        }
        public int Leader(int u) {
            if (parent[u] == u) return parent[u] = u;
            return parent[u] = Leader(parent[u]);
        }
    }
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        map = new HashMap<>();
        nodes = new ArrayList<>();
        DSU dsu = new DSU(n + 1);
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int current[] : edges) {
            int u = current[0];
            int v = current[1];
            int wt = current[2];
            if (dsu.Leader(u) != dsu.Leader(v)) dsu.merge(u , v);
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        vis = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                nodes.clear();
                dfs(i, -1);
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < nodes.size(); j++) {
                    int u = nodes.get(j);
                    for (int k = 0; k < adj.get(u).size(); k++) temp.add(adj.get(u).get(k).distance);
                }
                if (temp.size() == 0) continue;
                int and = temp.get(0);
                for (int ele : temp) and &= ele;
                if (and != -1) {
                    for (int ele : nodes) map.put(ele, and);
                }
            }
        }
        int res[] = new int[query.length];
        int k = 0;
        for (int current[] : query) {
            int src = current[0];
            int dst = current[1];
            if (dsu.Leader(src) != dsu.Leader(dst)) {
                res[k++] = -1;
                continue;
            }
            res[k++] = map.get(src);
        }
        return res;
    }
    private void dfs(int u , int par) {
        vis[u] = 1;
        nodes.add(u);
        for (int i = 0; i < adj.get(u).size(); i++) {
            int child = adj.get(u).get(i).node;
            if (vis[child] == 0) dfs(child , u);
        }
    }
    private int min_dist(int n , int src, int dst) {
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        for (int i = 0; i < adj.get(src).size(); i++) {
            int child_node = adj.get(src).get(i).node;
            int child_dist = adj.get(src).get(i).distance;
            dist[child_node] = child_dist;
            pq.offer(new Pair(child_node, dist[child_node]));
        }
        while (pq.size() > 0) {
            int current_node = pq.peek().node;
            int current_dist = pq.peek().distance;
            pq.poll();
            for (int i = 0; i < adj.get(current_node).size(); i++) {
                int child_node = adj.get(current_node).get(i).node;
                int child_dist = adj.get(current_node).get(i).distance;
                if (dist[child_node] > (current_dist & child_dist)) {
                    dist[child_node] = current_dist & child_dist;
                    pq.offer(new Pair(child_node, dist[child_node]));
                }
            }
        }
        if(dist[dst] == (int)(1e9)) return -1;
        return dist[dst];
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

