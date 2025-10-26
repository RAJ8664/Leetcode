# 1414. Shortest Path In A Grid With Obstacles Elimination

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1414-shortest-path-in-a-grid-with-obstacles-elimination){ .md-button }

---

<h2><a href="https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination">1414. Shortest Path in a Grid with Obstacles Elimination</a></h2><h3>Hard</h3><hr><p>You are given an <code>m x n</code> integer matrix <code>grid</code> where each cell is either <code>0</code> (empty) or <code>1</code> (obstacle). You can move up, down, left, or right from and to an empty cell in <strong>one step</strong>.</p>

<p>Return <em>the minimum number of <strong>steps</strong> to walk from the upper left corner </em><code>(0, 0)</code><em> to the lower right corner </em><code>(m - 1, n - 1)</code><em> given that you can eliminate <strong>at most</strong> </em><code>k</code><em> obstacles</em>. If it is not possible to find such walk return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/09/30/short1-grid.jpg" style="width: 244px; height: 405px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -&gt; (0,1) -&gt; (0,2) -&gt; (1,2) -&gt; (2,2) -&gt; <strong>(3,2)</strong> -&gt; (4,2).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/09/30/short2-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> We need to eliminate at least two obstacles to find such a walk.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 40</code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> <strong>or</strong> <code>1</code>.</li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 0</code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static class Tuple {
        int row, col, count;
        public Tuple(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
        @Override
        public String toString() {
            return "Tuple{" +
                   "row=" + row +
                   ", col=" + col +
                   ", count=" + count +
                   '}';
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.count, second.count);
        }
    }
    public int shortestPath(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int dist[][][] = new int[n + 1][m + 1][k + 1];
        for (int current[][] : dist)
            for (int current1[] : current)
                Arrays.fill(current1, (int)(1e9));

        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        if (grid[0][0] == 1)
            dist[0][0][1] = 0;
        else
            dist[0][0][0] = 0;

        pq.offer(new Tuple(0, 0, grid[0][0]));
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currCount = pq.peek().count;
            pq.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m) {
                    if (grid[newRow][newCol] == 1) {
                        if (currCount < k) {
                            if (dist[newRow][newCol][currCount + 1] > dist[currRow][currCol][currCount] + 1) {
                                dist[newRow][newCol][currCount + 1] = dist[currRow][currCol][currCount] + 1;
                                pq.offer(new Tuple(newRow, newCol, currCount + 1));
                            }
                        }
                    } else {
                        if (dist[newRow][newCol][currCount] > dist[currRow][currCol][currCount] + 1) {
                            dist[newRow][newCol][currCount] = dist[currRow][currCol][currCount] + 1;
                            pq.offer(new Tuple(newRow, newCol, currCount));
                        }
                    }
                }
            }
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++)
            mini = Math.min(mini, dist[n - 1][m - 1][i]);
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

