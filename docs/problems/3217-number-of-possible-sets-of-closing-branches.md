# 3217. Number Of Possible Sets Of Closing Branches

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-possible-sets-of-closing-branches/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3217-number-of-possible-sets-of-closing-branches){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-possible-sets-of-closing-branches">3217. Number of Possible Sets of Closing Branches</a></h2><h3>Hard</h3><hr><p>There is a company with <code>n</code> branches across the country, some of which are connected by roads. Initially, all branches are reachable from each other by traveling some roads.</p>

<p>The company has realized that they are spending an excessive amount of time traveling between their branches. As a result, they have decided to close down some of these branches (<strong>possibly none</strong>). However, they want to ensure that the remaining branches have a distance of at most <code>maxDistance</code> from each other.</p>

<p>The <strong>distance</strong> between two branches is the <strong>minimum</strong> total traveled length needed to reach one branch from another.</p>

<p>You are given integers <code>n</code>, <code>maxDistance</code>, and a <strong>0-indexed</strong> 2D array <code>roads</code>, where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> represents the <strong>undirected</strong> road between branches <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with length <code>w<sub>i</sub></code>.</p>

<p>Return <em>the number of possible sets of closing branches, so that any branch has a distance of at most </em><code>maxDistance</code><em> from any other</em>.</p>

<p><strong>Note</strong> that, after closing a branch, the company will no longer have access to any roads connected to it.</p>

<p><strong>Note</strong> that, multiple roads are allowed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/11/08/example11.png" style="width: 221px; height: 191px;" />
<pre>
<strong>Input:</strong> n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [2], after closing, active branches are [0,1] and they are reachable to each other within distance 2.
- The set [0,1], after closing, the active branch is [2].
- The set [1,2], after closing, the active branch is [0].
- The set [0,2], after closing, the active branch is [1].
- The set [0,1,2], after closing, there are no active branches.
It can be proven, that there are only 5 possible sets of closing branches.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/11/08/example22.png" style="width: 221px; height: 241px;" />
<pre>
<strong>Input:</strong> n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [], after closing, active branches are [0,1,2] and they are reachable to each other within distance 4.
- The set [0], after closing, active branches are [1,2] and they are reachable to each other within distance 2.
- The set [1], after closing, active branches are [0,2] and they are reachable to each other within distance 2.
- The set [0,1], after closing, the active branch is [2].
- The set [1,2], after closing, the active branch is [0].
- The set [0,2], after closing, the active branch is [1].
- The set [0,1,2], after closing, there are no active branches.
It can be proven, that there are only 7 possible sets of closing branches.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1, maxDistance = 10, roads = []
<strong>Output:</strong> 2
<strong>Explanation:</strong> The possible sets of closing branches are:
- The set [], after closing, the active branch is [0].
- The set [0], after closing, there are no active branches.
It can be proven, that there are only 2 possible sets of closing branches.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= maxDistance &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= roads.length &lt;= 1000</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 1000</code></li>
	<li>All branches are reachable from each other by traveling some roads.</li>
</ul>


---

## Solution

```java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

class Solution {
    private ArrayList<ArrayList<Integer >> sequences;
    private int count;
    private ArrayList<ArrayList<Pair >> adj;

    static class Pair {
        int node, weight;
        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + node + ", " + weight + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.node == node && current.weight == weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, weight);
        }
    }

    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.weight, second.weight);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.node, second.node);
        }
    }

    static class road_custom_sort implements Comparator<int[]> {
        @Override
        public int compare(int first[], int second[]) {
            int op1 = Integer.compare(first[2], second[2]);
            if (op1 != 0)
                return op1;
            int op2 = Integer.compare(first[1], second[1]);
            if (op2 != 0)
                return op2;
            return Integer.compare(first[0], second[0]);
        }
    }

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        sequences = new ArrayList<>();
        count = 0;

        getSequences(0, new ArrayList<>(), arr);

        Arrays.sort(roads, new road_custom_sort());

        for (ArrayList<Integer> seq : sequences) {
            if (check(seq, roads, maxDistance, n) == true)
                count++;
        }
        return count;
    }

    private boolean check(ArrayList<Integer> seq, int[][] roads, int maxDistance, int n) {
        adj = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int ele : seq)
            set.add(ele);
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());

        HashSet<Pair> occurredEdges = new HashSet<>();
        for (int edge[] : roads) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (occurredEdges.contains(new Pair(u, v)))
                continue;
            if (!set.contains(u) && !set.contains(v)) {
                adj.get(u).add(new Pair(v, w));
                adj.get(v).add(new Pair(u, w));
                occurredEdges.add(new Pair(u, v));
                occurredEdges.add(new Pair(v, u));
            }
        }

        /*set --> i don't want these nodes */

        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 10);
        if (set.size() == n)
            return true;
        int start_node = -1;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                start_node = i;
                break;
            }
        }

        HashSet<Integer> dfsGot = new HashSet<>();
        int vis[] = new int[n + 1];
        dfsReachCheck(start_node, -1, dfsGot, vis);

        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                if (!dfsGot.contains(i))
                    return false;
            }
        }
        return dfsDistCheck(start_node, adj, set, maxDistance, n, seq);
    }

    private boolean dfsDistCheck(int start_node, ArrayList<ArrayList<Pair >> adj, HashSet<Integer> set, int maxDistance, int n, ArrayList<Integer> seq) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (set.contains(i) || set.contains(j))
                    continue;
                if (computeDistance(i, j, n, adj) > maxDistance)
                    return false;
            }
        }
        return true;
    }

    private int computeDistance(int u, int v, int n, ArrayList<ArrayList<Pair >> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        pq.offer(new Pair(u, 0));
        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 10);
        dist[u] = 0;
        while (pq.size() > 0) {
            int curr_node = pq.peek().node;
            int curr_dist = pq.peek().weight;
            pq.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                int child_dist = adj.get(curr_node).get(i).weight;
                if (dist[child_node] > curr_dist + child_dist) {
                    dist[child_node] = curr_dist + child_dist;
                    pq.offer(new Pair(child_node, dist[child_node]));
                }
            }
        }
        return dist[v];
    }

    private void dfsReachCheck(int u, int par, HashSet<Integer> dfsGot, int vis[]) {
        dfsGot.add(u);
        vis[u] = 1;
        for (Pair x : adj.get(u)) {
            if (vis[x.node] == 0)
                dfsReachCheck(x.node, u, dfsGot, vis);
        }
    }

    private void getSequences(int ind, ArrayList<Integer> current, int arr[]) {
        if (ind == arr.length) {
            sequences.add(new ArrayList<>(current));
            return;
        }
        current.add(arr[ind]);
        getSequences(ind + 1, current, arr);
        current.remove(current.size() - 1);
        getSequences(ind + 1, current, arr);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

