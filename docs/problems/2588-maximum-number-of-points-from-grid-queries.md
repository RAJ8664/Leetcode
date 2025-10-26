# 2588. Maximum Number Of Points From Grid Queries

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2588-maximum-number-of-points-from-grid-queries){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-points-from-grid-queries">2588. Maximum Number of Points From Grid Queries</a></h2><h3>Hard</h3><hr><p>You are given an <code>m x n</code> integer matrix <code>grid</code> and an array <code>queries</code> of size <code>k</code>.</p>

<p>Find an array <code>answer</code> of size <code>k</code> such that for each integer <code>queries[i]</code> you start in the <strong>top left</strong> cell of the matrix and repeat the following process:</p>

<ul>
	<li>If <code>queries[i]</code> is <strong>strictly</strong> greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any <strong>adjacent</strong> cell in all <code>4</code> directions: up, down, left, and right.</li>
	<li>Otherwise, you do not get any points, and you end this process.</li>
</ul>

<p>After the process, <code>answer[i]</code> is the <strong>maximum</strong> number of points you can get. <strong>Note</strong> that for each query you are allowed to visit the same cell <strong>multiple</strong> times.</p>

<p>Return <em>the resulting array</em> <code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2025/03/15/image1.png" style="width: 571px; height: 152px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
<strong>Output:</strong> [5,8,1]
<strong>Explanation:</strong> The diagrams above show which cells we visit to get points for each query.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/20/yetgriddrawio-2.png" />
<pre>
<strong>Input:</strong> grid = [[5,2,1],[1,1,2]], queries = [3]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> We can not get any points because the value of the top left cell is already greater than or equal to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>k == queries.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[i][j], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int val, ind;
        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
        @Override
        public String toString() {
            return "(" + val + " " + ind + ")";
        }
    }
    static class Tuple {
        int row, col, val;
        public Tuple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + " " + val + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.val, second.val);
        }
    }
    static class custom_sort1 implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.val, second.val);
        }
    }
    public int[] maxPoints(int[][] grid, int[] queries) {
        int n = grid.length, m = grid[0].length;
        ArrayList<Pair> sorted_queries = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) sorted_queries.add(new Pair(queries[i], i));
        Collections.sort(sorted_queries, new custom_sort());
        int res[] = new int[queries.length];
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new custom_sort1());
        int vis[][] = new int[n + 1][m + 1];
        vis[0][0] = 1;
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        pq.offer(new Tuple(0, 0, grid[0][0]));
        int count = 0, current_ind = 0;
        while (current_ind < queries.length) {
            while (pq.size() > 0 && pq.peek().val < sorted_queries.get(current_ind).val) {
                count++;
                int cr = pq.peek().row;
                int cc = pq.peek().col;
                int cval = pq.peek().val;
                pq.poll();
                for (int dire[] : dir) {
                    int nr = cr + dire[0];
                    int nc = cc + dire[1];
                    if (nr < n && nr >= 0 && nc < m && nc >= 0 && vis[nr][nc] == 0) {
                        vis[nr][nc] = 1;
                        pq.offer(new Tuple(nr, nc, grid[nr][nc]));
                    }
                }
            }
            res[sorted_queries.get(current_ind).ind] = count;
            current_ind++;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

