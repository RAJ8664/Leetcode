# 1986. Largest Color Value In A Directed Graph


---

<h2><a href="https://leetcode.com/problems/largest-color-value-in-a-directed-graph">1986. Largest Color Value in a Directed Graph</a></h2><h3>Hard</h3><hr><p>There is a <strong>directed graph</strong> of <code>n</code> colored nodes and <code>m</code> edges. The nodes are numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a string <code>colors</code> where <code>colors[i]</code> is a lowercase English letter representing the <strong>color</strong> of the <code>i<sup>th</sup></code> node in this graph (<strong>0-indexed</strong>). You are also given a 2D array <code>edges</code> where <code>edges[j] = [a<sub>j</sub>, b<sub>j</sub>]</code> indicates that there is a <strong>directed edge</strong> from node <code>a<sub>j</sub></code> to node <code>b<sub>j</sub></code>.</p>

<p>A valid <strong>path</strong> in the graph is a sequence of nodes <code>x<sub>1</sub> -&gt; x<sub>2</sub> -&gt; x<sub>3</sub> -&gt; ... -&gt; x<sub>k</sub></code> such that there is a directed edge from <code>x<sub>i</sub></code> to <code>x<sub>i+1</sub></code> for every <code>1 &lt;= i &lt; k</code>. The <strong>color value</strong> of the path is the number of nodes that are colored the <strong>most frequently</strong> occurring color along that path.</p>

<p>Return <em>the <strong>largest color value</strong> of any valid path in the given graph, or </em><code>-1</code><em> if the graph contains a cycle</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/04/21/leet1.png" style="width: 400px; height: 182px;" /></p>

<pre>
<strong>Input:</strong> colors = &quot;abaca&quot;, edges = [[0,1],[0,2],[2,3],[3,4]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The path 0 -&gt; 2 -&gt; 3 -&gt; 4 contains 3 nodes that are colored <code>&quot;a&quot; (red in the above image)</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/04/21/leet2.png" style="width: 85px; height: 85px;" /></p>

<pre>
<strong>Input:</strong> colors = &quot;a&quot;, edges = [[0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is a cycle from 0 to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == colors.length</code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>colors</code> consists of lowercase English letters.</li>
	<li><code>0 &lt;= a<sub>j</sub>, b<sub>j</sub>&nbsp;&lt; n</code></li>
</ul>

## Solution

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private HashMap<Integer, int[]> dp;
    private int vis[];
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        if (n == 0) return 1;
        adj = new ArrayList<>();
        vis = new int[n + 1];
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        if (checkCycle(n)) return -1;
        dp = new HashMap<>();
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (!dp.containsKey(i)) {
                int res[] = dfs(n, i, colors);
                for (int ele : res) maxi = Math.max(maxi, ele);
            }
        }
        return maxi;
    }
    private int[] dfs(int n, int u, String s) {
        if (dp.containsKey(u)) return dp.get(u);
        int ans[] = new int[26];
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                int child_ans[] = dfs(n, v, s);
                for (int i = 0; i < 26; i++) ans[i] = Math.max(ans[i], child_ans[i]);
            }
        }
        vis[u] = 0;
        ans[s.charAt(u) - 'a']++;
        dp.put(u, ans);
        return ans;
    }

    private boolean checkCycle(int n) {
        int indegree[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int v : adj.get(i)) indegree[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        int count = 0;
        while (q.size() > 0) {
            int curr_node = q.poll();
            count++;
            for (int v : adj.get(curr_node)) {
                indegree[v]--;
                if (indegree[v] == 0) q.add(v);
            }
        }
        if (count == n) return false;
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

