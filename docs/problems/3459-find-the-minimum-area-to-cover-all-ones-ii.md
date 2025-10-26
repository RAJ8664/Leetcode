# 3459. Find The Minimum Area To Cover All Ones Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3459-find-the-minimum-area-to-cover-all-ones-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii">3459. Find the Minimum Area to Cover All Ones II</a></h2><h3>Hard</h3><hr><p>You are given a 2D <strong>binary</strong> array <code>grid</code>. You need to find 3 <strong>non-overlapping</strong> rectangles having <strong>non-zero</strong> areas with horizontal and vertical sides such that all the 1&#39;s in <code>grid</code> lie inside these rectangles.</p>

<p>Return the <strong>minimum</strong> possible sum of the area of these rectangles.</p>

<p><strong>Note</strong> that the rectangles are allowed to touch.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,1],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/05/14/example0rect21.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 280px; height: 198px;" /></p>

<ul>
	<li>The 1&#39;s at <code>(0, 0)</code> and <code>(1, 0)</code> are covered by a rectangle of area 2.</li>
	<li>The 1&#39;s at <code>(0, 2)</code> and <code>(1, 2)</code> are covered by a rectangle of area 2.</li>
	<li>The 1 at <code>(1, 1)</code> is covered by a rectangle of area 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,1,0],[0,1,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/05/14/example1rect2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 356px; height: 198px;" /></p>

<ul>
	<li>The 1&#39;s at <code>(0, 0)</code> and <code>(0, 2)</code> are covered by a rectangle of area 3.</li>
	<li>The 1 at <code>(1, 1)</code> is covered by a rectangle of area 1.</li>
	<li>The 1 at <code>(1, 3)</code> is covered by a rectangle of area 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either 0 or 1.</li>
	<li>The input is generated such that there are at least three 1&#39;s in <code>grid</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minimumSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int res = Integer.MAX_VALUE;
        res = Math.min(res, getAns(grid));

        grid = rightRotate(grid);

        res = Math.min(res, getAns(grid));
        return res;
    }

    private int getAns(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int r1 = minSum1(0, i, 0, m - 1, grid);
                int r2 = minSum1(i + 1, n - 1, 0, j, grid);
                int r3 = minSum1(i + 1, n - 1, j + 1, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }

        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int r1 = minSum1(0, i, 0, j, grid);
                int r2 = minSum1(0, i, j + 1, m - 1, grid);
                int r3 = minSum1(i + 1, n - 1, 0, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }

        for (int i1 = 0; i1 <= n - 3; i1++) {
            for (int i2 = i1 + 1; i2 <= n - 2; i2++) {
                int r1 = minSum1(0, i1, 0, m - 1, grid);
                int r2 = minSum1(i1 + 1, i2, 0, m - 1, grid);
                int r3 = minSum1(i2 + 1, n - 1, 0, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }
        return res;
    }

    private int[][] rightRotate(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                res[j][n - 1 - i] = grid[i][j];
        }
        grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                grid[i][j] = res[i][j];
        }
        return grid;
    }

    private int minSum1(int startRow, int endRow, int startCol, int endCol, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (grid[i][j] == 1) {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        if (maxX == Integer.MIN_VALUE)
            return 0;
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

