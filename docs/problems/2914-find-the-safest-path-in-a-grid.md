# 2914. Find The Safest Path In A Grid

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-safest-path-in-a-grid/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2914-find-the-safest-path-in-a-grid){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-safest-path-in-a-grid">2914. Find the Safest Path in a Grid</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> 2D matrix <code>grid</code> of size <code>n x n</code>, where <code>(r, c)</code> represents:</p>

<ul>
	<li>A cell containing a thief if <code>grid[r][c] = 1</code></li>
	<li>An empty cell if <code>grid[r][c] = 0</code></li>
</ul>

<p>You are initially positioned at cell <code>(0, 0)</code>. In one move, you can move to any adjacent cell in the grid, including cells containing thieves.</p>

<p>The <strong>safeness factor</strong> of a path on the grid is defined as the <strong>minimum</strong> manhattan distance from any cell in the path to any thief in the grid.</p>

<p>Return <em>the <strong>maximum safeness factor</strong> of all paths leading to cell </em><code>(n - 1, n - 1)</code><em>.</em></p>

<p>An <strong>adjacent</strong> cell of cell <code>(r, c)</code>, is one of the cells <code>(r, c + 1)</code>, <code>(r, c - 1)</code>, <code>(r + 1, c)</code> and <code>(r - 1, c)</code> if it exists.</p>

<p>The <strong>Manhattan distance</strong> between two cells <code>(a, b)</code> and <code>(x, y)</code> is equal to <code>|a - x| + |b - y|</code>, where <code>|val|</code> denotes the absolute value of val.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example1.png" style="width: 362px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0],[0,0,0],[0,0,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example2.png" style="width: 362px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,1],[0,0,0],[0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example3.png" style="width: 362px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length == n &lt;= 400</code></li>
	<li><code>grid[i].length == n</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>There is at least one thief in the <code>grid</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    private int min[][];
    private static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class State {
        int row, col, pRow, pCol;
        public State(int row, int col, int pRow, int pCol) {
            this.row = row;
            this.col = col;
            this.pRow = pRow;
            this.pCol = pCol;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + " " + pRow + " " + pCol + ")";
        }
    }
    static class Tuple {
        int row, col, cost;
        public Tuple(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + " " + cost + ")";
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(second.cost, first.cost);
        }
    }
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(), m = grid.get(0).size();
        /* How can we find the closest or minimum hamming distance wala thief for each cell ? */
        min = new int [n][m];
        for (int curr[] : min)
            Arrays.fill(curr, (int)(1e9));
        Queue<State> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new State(i, j, i, j));
                    min[i][j] = 0;
                }
            }
        }
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col, pRow = q.peek().pRow, pCol = q.peek().pCol;
            q.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int newDist = Math.abs(pRow - newRow) + Math.abs(pCol - newCol);
                    if (min[newRow][newCol] > newDist) {
                        min[newRow][newCol] = newDist;
                        q.offer(new State(newRow, newCol, pRow, pCol));
                    }
                }
            }
        }
        int low = 0, high = (int)(1e9), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, grid)) {
                ans = mid;
                low = mid + 1;
            }
            else 
                high = mid - 1;
        }
        return ans;
    }
    private boolean ok(int target, List<List<Integer>> arr) {
        int n = arr.size(), m = arr.get(0).size();
        if (min[0][0] < target) return false;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        int dist[][] = new int[n][m];
        for (int curr[] : dist) 
            Arrays.fill(curr, (int)(-1e9));
        dist[0][0] = min[0][0];
        pq.offer(new Tuple(0, 0, min[0][0]));
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currCost = pq.peek().cost;
            pq.poll();
            if (currRow == n - 1 && currCol == m - 1) 
                return true;
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m) {
                    if (dist[newRow][newCol] < Math.min(min[newRow][newCol], currCost)) {
                        if (Math.min(min[newRow][newCol], currCost) >= target) {
                            dist[newRow][newCol] = Math.min(min[newRow][newCol], currCost);
                            pq.offer(new Tuple(newRow, newCol, dist[newRow][newCol]));
                        }
                    }
                }
            }
        }
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

