# 3729. Unit Conversion I


---

<h2><a href="https://leetcode.com/problems/unit-conversion-i">3729. Unit Conversion I</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> types of units indexed from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>conversions</code> of length <code>n - 1</code>, where <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>. This indicates that a single unit of type <code>sourceUnit<sub>i</sub></code> is equivalent to <code>conversionFactor<sub>i</sub></code> units of type <code>targetUnit<sub>i</sub></code>.</p>

<p>Return an array <code>baseUnitConversion</code> of length <code>n</code>, where <code>baseUnitConversion[i]</code> is the number of units of type <code>i</code> equivalent to a single unit of type 0. Since the answer may be large, return each <code>baseUnitConversion[i]</code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[1,2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,6]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 2 using <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
</ul>
<img alt="" src="https://assets.leetcode.com/uploads/2025/03/12/example1.png" style="width: 545px; height: 118px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,3],[1,3,4],[1,4,5],[2,5,2],[4,6,3],[5,7,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,8,10,6,30,24]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 3 units of type 2 using <code>conversions[1]</code>.</li>
	<li>Convert a single unit of type 0 into 8 units of type 3 using <code>conversions[0]</code>, then <code>conversions[2]</code>.</li>
	<li>Convert a single unit of type 0 into 10 units of type 4 using <code>conversions[0]</code>, then <code>conversions[3]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 5 using <code>conversions[1]</code>, then <code>conversions[4]</code>.</li>
	<li>Convert a single unit of type 0 into 30 units of type 6 using <code>conversions[0]</code>, <code>conversions[3]</code>, then <code>conversions[5]</code>.</li>
	<li>Convert a single unit of type 0 into 24 units of type 7 using <code>conversions[1]</code>, <code>conversions[4]</code>, then <code>conversions[6]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that unit 0 can be converted into any other unit through a <strong>unique</strong> combination of conversions without using any conversions in the opposite direction.</li>
</ul>


## Solution

```java
class Solution {
    private ArrayList<ArrayList<Pair>> adj;
    private long mod = (long)(1e9 + 7);
    static class Pair {
        int node;
        long dist;
        public Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return "(" + node + " " + dist + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Long.compare(first.dist, second.dist);
        }
    }
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int current[] : conversions) {
            int u = current[0], v = current[1], wt = current[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        long dp[] = new long[n + 1];
        Arrays.fill(dp, (long)(2e19));
        dp[0] = 1;
        pq.offer(new Pair(0, 1));
        while (pq.size() > 0) {
            int curr_node = pq.peek().node;
            long curr_dist = pq.peek().dist;
            pq.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                long child_dist = adj.get(curr_node).get(i).dist;
                if (dp[child_node] > (curr_dist * 1L * child_dist)) {
                    dp[child_node] = (int)((curr_dist * 1L * child_dist) % mod);
                    pq.offer(new Pair(child_node, dp[child_node]));
                }
            }
        }
        int res[] = new int[n + 1];
        for (int i = 0; i <= n; i++) res[i] = (int)(dp[i]);
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

