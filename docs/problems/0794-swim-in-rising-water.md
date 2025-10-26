# 794. Swim In Rising Water

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/swim-in-rising-water/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0794-swim-in-rising-water){ .md-button }

---

<h2><a href="https://leetcode.com/problems/swim-in-rising-water">794. Swim in Rising Water</a></h2><h3>Hard</h3><hr><p>You are given an <code>n x n</code> integer matrix <code>grid</code> where each value <code>grid[i][j]</code> represents the elevation at that point <code>(i, j)</code>.</p>

<p>It starts raining, and water gradually rises over time. At time <code>t</code>, the water level is <code>t</code>, meaning <strong>any</strong> cell with elevation less than equal to <code>t</code> is submerged or reachable.</p>

<p>You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most <code>t</code>. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>Return <em>the minimum time until you can reach the bottom right square </em><code>(n - 1, n - 1)</code><em> if you start at the top left square </em><code>(0, 0)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/06/29/swim1-grid.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[0,2],[1,3]]
<strong>Output:</strong> 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/06/29/swim2-grid-1.jpg" style="width: 404px; height: 405px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>Output:</strong> 16
<strong>Explanation:</strong> The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;&nbsp;n<sup>2</sup></code></li>
	<li>Each value <code>grid[i][j]</code> is <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Tuple {
        int row, col, val;
        public Tuple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.val, second.val);
        }
    } 

    public int swimInWater(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int dist[][] = new int[n][m];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));

        dist[0][0] = grid[0][0]; 
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Tuple(0, 0, grid[0][0]));

        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currVal = pq.peek().val;
            pq.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0) {
                    if (dist[newRow][newCol] > Math.max(grid[newRow][newCol], currVal)) {
                        dist[newRow][newCol] = Math.max(grid[newRow][newCol], currVal);
                        pq.offer(new Tuple(newRow, newCol, dist[newRow][newCol]));
                    }
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

