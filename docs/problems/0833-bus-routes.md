# 833. Bus Routes

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/bus-routes/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0833-bus-routes){ .md-button }

---

<h2><a href="https://leetcode.com/problems/bus-routes">833. Bus Routes</a></h2><h3>Hard</h3><hr><p>You are given an array <code>routes</code> representing bus routes where <code>routes[i]</code> is a bus route that the <code>i<sup>th</sup></code> bus repeats forever.</p>

<ul>
	<li>For example, if <code>routes[0] = [1, 5, 7]</code>, this means that the <code>0<sup>th</sup></code> bus travels in the sequence <code>1 -&gt; 5 -&gt; 7 -&gt; 1 -&gt; 5 -&gt; 7 -&gt; 1 -&gt; ...</code> forever.</li>
</ul>

<p>You will start at the bus stop <code>source</code> (You are not on any bus initially), and you want to go to the bus stop <code>target</code>. You can travel between bus stops by buses only.</p>

<p>Return <em>the least number of buses you must take to travel from </em><code>source</code><em> to </em><code>target</code>. Return <code>-1</code> if it is not possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> routes = [[1,2,7],[3,6,7]], source = 1, target = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= routes.length &lt;= 500</code>.</li>
	<li><code>1 &lt;= routes[i].length &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>routes[i]</code> are <strong>unique</strong>.</li>
	<li><code>sum(routes[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= routes[i][j] &lt; 10<sup>6</sup></code></li>
	<li><code>0 &lt;= source, target &lt; 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node, cost;
        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "(" + node + " " + cost + ")";
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.cost, second.cost);
            return op1;
        }
    }
    
    private ArrayList<ArrayList<Pair>> adj;
   
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        if (source == target)
            return 0;

        adj = new ArrayList<>();
        for (int i = 0; i <= routes.length; i++)
            adj.add(new ArrayList<>());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int ele : routes[i - 1]) {
                if (!map.containsKey(ele)) 
                    map.put(ele, new ArrayList<>());
                map.get(ele).add(i);
            }
        }

        int dist[] = new int[(int)(n + 1)];
        Arrays.fill(dist, (int)(1e9));
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(new customSort());
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> val = new ArrayList<>();
            val = curr.getValue();
            for (int i = 0; i < val.size() - 1; i++) {
                int u = val.get(i), v = val.get(i + 1);
                if (u == v) continue;
                adj.get(u).add(new Pair(v, 1));
                adj.get(v).add(new Pair(u, 1));
            }
            if (val.size() > 1) {
                int u = val.get(0), v = val.get(val.size() - 1);
                if (u == v) continue;
                adj.get(u).add(new Pair(v, 1));
                adj.get(v).add(new Pair(u, 1));
            }
            
            if (curr.getKey() == source) {
                for (int ele : val) {
                    dist[ele] = 0;
                    pq.offer(new Pair(ele, 0));
                }
            }
        }

        while (pq.size() > 0){
            int currNode = pq.peek().node, currCost = pq.peek().cost;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childDist = adj.get(currNode).get(i).cost;
                if (dist[childNode] > childDist + currCost) {
                    dist[childNode] = childDist + currCost;
                    pq.offer(new Pair(childNode, dist[childNode]));
                } 
            }
        }

        int mini = Integer.MAX_VALUE;
        boolean flag = false;
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> val = new ArrayList<>();
            val = curr.getValue();
            if (curr.getKey() == target) {
                for (int ele : val) {
                    if (dist[ele] != (int)(1e9)) flag = true;
                    mini = Math.min(mini, dist[ele]);
                }
            }
        }
        if (flag == false) return -1;
        return mini + 1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

