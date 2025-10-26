# 803. Cheapest Flights Within K Stops

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/cheapest-flights-within-k-stops/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0803-cheapest-flights-within-k-stops){ .md-button }

---

<h2><a href="https://leetcode.com/problems/cheapest-flights-within-k-stops">803. Cheapest Flights Within K Stops</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> cities connected by some number of flights. You are given an array <code>flights</code> where <code>flights[i] = [from<sub>i</sub>, to<sub>i</sub>, price<sub>i</sub>]</code> indicates that there is a flight from city <code>from<sub>i</sub></code> to city <code>to<sub>i</sub></code> with cost <code>price<sub>i</sub></code>.</p>

<p>You are also given three integers <code>src</code>, <code>dst</code>, and <code>k</code>, return <em><strong>the cheapest price</strong> from </em><code>src</code><em> to </em><code>dst</code><em> with at most </em><code>k</code><em> stops. </em>If there is no such route, return<em> </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/03/18/cheapest-flights-within-k-stops-3drawio.png" style="width: 332px; height: 392px;" />
<pre>
<strong>Input:</strong> n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
<strong>Output:</strong> 700
<strong>Explanation:</strong>
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/03/18/cheapest-flights-within-k-stops-1drawio.png" style="width: 332px; height: 242px;" />
<pre>
<strong>Input:</strong> n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
<strong>Output:</strong> 200
<strong>Explanation:</strong>
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/03/18/cheapest-flights-within-k-stops-2drawio.png" style="width: 332px; height: 242px;" />
<pre>
<strong>Input:</strong> n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
<strong>Output:</strong> 500
<strong>Explanation:</strong>
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= flights.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>flights[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>There will not be any multiple flights between two cities.</li>
	<li><code>0 &lt;= src, dst, k &lt; n</code></li>
	<li><code>src != dst</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int dist[][];
    private ArrayList<ArrayList<Pair>> adj;
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
    static class Tuple {
        int node, weight, used;
        public Tuple(int node, int weight, int used) {
            this.node = node;
            this.weight = weight;
            this.used = used;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + " " + used + ")";
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.weight, second.weight);
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        dist = new int[n + 1][k + 2];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));
        dist[src][0] = 0;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : flights) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Tuple(src, 0, 0));
        while (pq.size() > 0) {
            int currNode = pq.peek().node, currCost = pq.peek().weight, currUsed = pq.peek().used;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childWeight = adj.get(currNode).get(i).weight;
                if (currUsed + 1 > k + 1) continue;
                if (dist[childNode][currUsed + 1] > dist[currNode][currUsed] + childWeight) {
                    dist[childNode][currUsed + 1] = dist[currNode][currUsed] + childWeight;
                    pq.offer(new Tuple(childNode, dist[childNode][currUsed + 1], currUsed + 1)); 
                } 
            }    
        }
        int mini = (int)(1e9);
        for (int i = 0; i <= k + 1; i++) {
            mini = Math.min(mini, dist[dst][i]); 
        }
        if (mini == (int)(1e9))
            return -1;
        return mini;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

