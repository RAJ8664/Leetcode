# 2764. Maximum Number Of Fish In A Grid

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2764-maximum-number-of-fish-in-a-grid){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-fish-in-a-grid">2764. Maximum Number of Fish in a Grid</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> 2D matrix <code>grid</code> of size <code>m x n</code>, where <code>(r, c)</code> represents:</p>

<ul>
	<li>A <strong>land</strong> cell if <code>grid[r][c] = 0</code>, or</li>
	<li>A <strong>water</strong> cell containing <code>grid[r][c]</code> fish, if <code>grid[r][c] &gt; 0</code>.</li>
</ul>

<p>A fisher can start at any <strong>water</strong> cell <code>(r, c)</code> and can do the following operations any number of times:</p>

<ul>
	<li>Catch all the fish at cell <code>(r, c)</code>, or</li>
	<li>Move to any adjacent <strong>water</strong> cell.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of fish the fisher can catch if he chooses his starting cell optimally, or </em><code>0</code> if no water cell exists.</p>

<p>An <strong>adjacent</strong> cell of the cell <code>(r, c)</code>, is one of the cells <code>(r, c + 1)</code>, <code>(r, c - 1)</code>, <code>(r + 1, c)</code> or <code>(r - 1, c)</code> if it exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/03/29/example.png" style="width: 241px; height: 161px;" />
<pre>
<strong>Input:</strong> grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The fisher can start at cell <code>(1,3)</code> and collect 3 fish, then move to cell <code>(2,3)</code>&nbsp;and collect 4 fish.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/03/29/example2.png" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The fisher can start at cells (0,0) or (3,3) and collect a single fish. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int vis[][];
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }
    public int findMaxFish(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        vis = new int[n][m];

        int maxi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] > 0) {
                    int currFish = BFS(i, j, grid);
                    maxi = Math.max(maxi, currFish);
                }
            }
        }
        return maxi;
    }

    private int BFS(int row, int col, int arr[][]) {
        int n = arr.length, m = arr[0].length;
        int totalFish = 0;
        vis[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            totalFish += arr[currRow][currCol];
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0 && vis[newRow][newCol] == 0 && arr[newRow][newCol] > 0) {
                    vis[newRow][newCol] = 1;
                    q.offer(new Pair(newRow, newCol));
                }
            }
        }
        return totalFish;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

