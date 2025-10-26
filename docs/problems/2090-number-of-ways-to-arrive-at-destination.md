# 2090. Number Of Ways To Arrive At Destination

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2090-number-of-ways-to-arrive-at-destination){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-ways-to-arrive-at-destination">2090. Number of Ways to Arrive at Destination</a></h2><h3>Medium</h3><hr><p>You are in a city that consists of <code>n</code> intersections numbered from <code>0</code> to <code>n - 1</code> with <strong>bi-directional</strong> roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.</p>

<p>You are given an integer <code>n</code> and a 2D integer array <code>roads</code> where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> means that there is a road between intersections <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. You want to know in how many ways you can travel from intersection <code>0</code> to intersection <code>n - 1</code> in the <strong>shortest amount of time</strong>.</p>

<p>Return <em>the <strong>number of ways</strong> you can arrive at your destination in the <strong>shortest amount of time</strong></em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2025/02/14/1976_corrected.png" style="width: 255px; height: 400px;" />
<pre>
<strong>Input:</strong> n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 â 6
- 0 â 4 â 6
- 0 â 1 â 2 â 5 â 6
- 0 â 1 â 3 â 5 â 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, roads = [[1,0,10]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n - 1 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>There is at most one road connecting any two intersections.</li>
	<li>You can reach any intersection from any other intersection.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        long distance;
        int node;
        public Pair(long distance , int node) {
            this.distance = distance;
            this.node = node;
        }
    }
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for(int current[] : roads) {
            int u = current[0];
            int v = current[1];
            long dist = (long)current[2];
            adj.get(u).add(new Pair(dist,v));
            adj.get(v).add(new Pair(dist,u));
        }
        int res = solve(adj,n);
        return res;
    }
    public static int solve(ArrayList<ArrayList<Pair>> adj,int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> (int)x.distance - (int)y.distance);
        long dist[] = new long[n + 1];
        Arrays.fill(dist, (long)(1e18));
        int ways[] = new int[n + 1];
        Arrays.fill(ways,0);
        ways[0] = 1;
        dist[0] = 0;
        pq.offer(new Pair(0,0));
        while(!pq.isEmpty()) {
            int curr_node = pq.peek().node;
            long curr_dist = (long)pq.peek().distance;
            pq.poll();
            for(int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                long child_dist = (long)adj.get(curr_node).get(i).distance;
                if(dist[child_node] > (long)(curr_dist + child_dist)) {
                    dist[child_node] = (long)(curr_dist + child_dist);
                    ways[child_node] = ways[curr_node];
                    pq.offer(new Pair(dist[child_node] , child_node));
                }
                else if(dist[child_node] == (long)(curr_dist + child_dist)) {
                    ways[child_node] = (ways[child_node] + ways[curr_node]) % ((int)(1e9 + 7));
                }
            }
        }
        return ways[n - 1];
    } 
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

