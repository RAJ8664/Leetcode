# 685. Redundant Connection Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/redundant-connection-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0685-redundant-connection-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/redundant-connection-ii">685. Redundant Connection II</a></h2><h3>Hard</h3><hr><p>In this problem, a rooted tree is a <b>directed</b> graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.</p>

<p>The given input is a directed graph that started as a rooted tree with <code>n</code> nodes (with distinct values from <code>1</code> to <code>n</code>), with one additional directed edge added. The added edge has two different vertices chosen from <code>1</code> to <code>n</code>, and was not an edge that already existed.</p>

<p>The resulting graph is given as a 2D-array of <code>edges</code>. Each element of <code>edges</code> is a pair <code>[u<sub>i</sub>, v<sub>i</sub>]</code> that represents a <b>directed</b> edge connecting nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>, where <code>u<sub>i</sub></code> is a parent of child <code>v<sub>i</sub></code>.</p>

<p>Return <em>an edge that can be removed so that the resulting graph is a rooted tree of</em> <code>n</code> <em>nodes</em>. If there are multiple answers, return the answer that occurs last in the given 2D-array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/graph1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> [2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/graph2.jpg" style="width: 222px; height: 382px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
<strong>Output:</strong> [4,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int count;
    private int vis[];
    private int indegree[];
    public int[] findRedundantDirectedConnection(int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= edges.length + 1; i++)
            adj.add(new ArrayList<>());
        for (int i = edges.length - 1; i >= 0; i--) {
            //i don't want to take the edge i, can we form a tree;
            for (int j = 0; j <= edges.length; j++)
                adj.get(j).clear();
            indegree = new int[edges.length + 1];
            for (int j = 0; j < edges.length; j++) {
                if (j != i) {
                    int u = edges[j][0], v = edges[j][1];
                    adj.get(u).add(v);
                    indegree[v]++;
                }
            }
            vis = new int[edges.length + 1];
            count = 0;
            int start = -1;
            for (int j = 1; j <= edges.length; j++) {
                if (indegree[j] == 0)  {
                    start = j;
                    break;
                }
            }
            if (start == -1) 
                continue;
            dfs(start, -1);
            if (count == edges.length) 
                return new int[]{edges[i][0], edges[i][1]}; 
        }
        return new int[]{-1, -1};
    }
    private void dfs(int u, int par) {
        count++;
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) 
                dfs(v, u);
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

