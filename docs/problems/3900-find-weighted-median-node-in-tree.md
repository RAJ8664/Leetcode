# 3900. Find Weighted Median Node In Tree


---

<h2><a href="https://leetcode.com/problems/find-weighted-median-node-in-tree">3900. Find Weighted Median Node in Tree</a></h2><h3>Hard</h3><hr><p>You are given an integer <code>n</code> and an <strong>undirected, weighted</strong> tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates an edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>The <strong>weighted median node</strong> is defined as the <strong>first</strong> node <code>x</code> on the path from <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> such that the sum of edge weights from <code>u<sub>i</sub></code> to <code>x</code> is <strong>greater than or equal to half</strong> of the total path weight.</p>

<p>You are given a 2D integer array <code>queries</code>. For each <code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>, determine the weighted median node along the path from <code>u<sub>j</sub></code> to <code>v<sub>j</sub></code>.</p>

<p>Return an array <code>ans</code>, where <code>ans[j]</code> is the node index of the weighted median for <code>queries[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,7]], queries = [[1,0],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2025/05/26/screenshot-2025-05-26-at-193447.png" style="width: 200px; height: 64px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 0]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 7 &gt;= 3.5</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 7 &gt;= 3.5</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[2,0,4]], queries = [[0,1],[2,0],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0,2]</span></p>

<p><strong>E</strong><strong>xplanation:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2025/05/26/screenshot-2025-05-26-at-193610.png" style="width: 180px; height: 149px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 2 &gt;= 1</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 0]</code></td>
			<td style="border: 1px solid black;"><code>2 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[4]</code></td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Sum from <code>2 &rarr; 0 = 4 &gt;= 2</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 4]</code></td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 2 &lt; 3</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 4 = 6 &gt;= 3</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,2],[0,2,5],[1,3,1],[2,4,3]], queries = [[3,4],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2025/05/26/screenshot-2025-05-26-at-193857.png" style="width: 150px; height: 229px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[3, 4]</code></td>
			<td style="border: 1px solid black;"><code>3 &rarr; 1 &rarr; 0 &rarr; 2 &rarr; 4</code></td>
			<td style="border: 1px solid black;"><code>[1, 2, 5, 3]</code></td>
			<td style="border: 1px solid black;">11</td>
			<td style="border: 1px solid black;">5.5</td>
			<td style="border: 1px solid black;">Sum from <code>3 &rarr; 1 = 1 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 0 = 1 + 2 = 3 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 2 = 1 + 2 + 5 = 8 &gt;= 5.5</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 5]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">
			<p>Sum from <code>1 &rarr; 0 = 2 &lt; 3.5</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 5 = 7 &gt;= 3.5</code>, median is node 2.</p>
			</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[j] == [u<sub>j</sub>, v<sub>j</sub>]</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>


## Solution

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Pair {
        int node;
        long weight;
        public Pair(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + ")";
        }
    }
    private ArrayList<ArrayList<Pair >> adj;
    private long pref[];
    private int dp[][];
    private int depth[];
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            u++;
            v++;
            adj.get(u).add(new Pair(v, w * 1L));
            adj.get(v).add(new Pair(u, w * 1L));
        }
        Build_pref(n);
        dp = new int[n + 1][19];
        depth = new int[n + 1];
        dfs(1, 0);
        int res[] = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0] + 1, v = queries[i][1] + 1;
            if (u == v) {
                res[i] = u - 1;
                continue;
            }
            int lca = lca(u, v);
            long total_sum = pref[u] + pref[v] - 2 * pref[lca];

            /* u, . , . , lca, . . . , v */
            /* first check from u to lca if there exist some node */
            /* next check from lca to v */

            int check_left = check_left(u, lca, total_sum);
            if (check_left != -1)
                res[i] = check_left - 1;
            else
                res[i] = check_right(v, lca, total_sum, pref[u] - pref[lca]) - 1;
        }
        return res;
    }
    private int check_left(int u, int lca, long total) {
        int low = 0, high = depth[u] - depth[lca], ans = -1;
        double req = (double)(total * 1.0 / 2 * 1.0);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long sum = pref[u] - pref[find_kth_parent(u, mid)];
            if (sum >= req) {
                ans = find_kth_parent(u, mid);
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
    private int check_right(int v, int lca, long total, long prev_sum) {
        int low = 0, high = depth[v] - depth[lca], ans = -1;
        double req = (double)(total * 1.0 / 2 * 1.0);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long sum = prev_sum + pref[find_kth_parent(v, mid)] - pref[lca];
            if (sum >= req) {
                ans = find_kth_parent(v, mid);
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = find_kth_parent(v, diff);
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
    private int find_kth_parent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1)
                u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }
    private void dfs(int u, int par) {
        dp[u][0] = par;
        for (int i = 1; i <= 18; i++)
            dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int i = 0; i < adj.get(u).size(); i++) {
            int v = adj.get(u).get(i).node;
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
            }
        }
    }
    private void Build_pref(int n) {
        pref = new long[n + 1];
        int vis[] = new int[n + 1];
        pref[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        vis[1] = 1;
        while (q.size() > 0) {
            int curr_node = q.peek();
            q.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                long child_dist = adj.get(curr_node).get(i).weight;
                if (vis[child_node] == 0) {
                    vis[child_node] = 1;
                    pref[child_node] = pref[curr_node] + child_dist;
                    q.offer(child_node);
                }
            }
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

