# 1311. Largest Magic Square

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-magic-square/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1311-largest-magic-square){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-magic-square">1311. Largest Magic Square</a></h2><h3>Medium</h3><hr><p>A <code>k x k</code> <strong>magic square</strong> is a <code>k x k</code> grid filled with integers such that every row sum, every column sum, and both diagonal sums are <strong>all equal</strong>. The integers in the magic square <strong>do not have to be distinct</strong>. Every <code>1 x 1</code> grid is trivially a <strong>magic square</strong>.</p>

<p>Given an <code>m x n</code> integer <code>grid</code>, return <em>the <strong>size</strong> (i.e., the side length </em><code>k</code><em>) of the <strong>largest magic square</strong> that can be found within this grid</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/29/magicsquare-grid.jpg" style="width: 413px; height: 335px;" />
<pre>
<strong>Input:</strong> grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/29/magicsquare2-grid.jpg" style="width: 333px; height: 255px;" />
<pre>
<strong>Input:</strong> grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private HashSet<Integer> sums;
    public int largestMagicSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int maxi = 1;
        for (int k = n; k >= 2; k--) {
            sums = new HashSet<>();
            if (check(k, grid)) return k;
        }
        return 1;
    }
    private boolean check(int len, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int r1 = i, c1 = j;
                int r2 = r1 + len - 1, c2 = c1 + len - 1;
                if (r2 >= n || c2 >= m) continue;
                check_row(r1, c1, r2, c2, grid); check_col(r1, c1, r2, c2, grid); check_diagonal(r1, c1, r2, c2, grid);
                if (sums.size() == 1) return true;
                sums = new HashSet<>();
            }
        }
        return false;
    }
    private void check_row(int r1, int c1, int r2, int c2, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        for (int i = r1; i <= r2; i++) {
            int sum = 0;
            for (int j = c1; j <= c2; j++) sum += grid[i][j];
            sums.add(sum);
        }
    }
    private void check_col(int r1, int c1, int r2, int c2, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        for (int j = c1; j <= c2; j++) {
            int sum = 0;
            for (int i = r1; i <= r2; i++) sum += grid[i][j];
            sums.add(sum);
        }
    }
    private void check_diagonal(int r1, int c1, int r2, int c2, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int sum = 0;
        int i = r1, j = c1;
        while (i != r2 && j != c2) {
            sum += grid[i][j];
            i++;
            j++;
        }
        sum += grid[r2][c2];
        sums.add(sum);
        sum = 0;
        i = r1; j = c2;
        while (i != r2 && j != c1) {
            sum += grid[i][j];
            i++;
            j--;
        }
        sum += grid[r2][c1];
        sums.add(sum);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

