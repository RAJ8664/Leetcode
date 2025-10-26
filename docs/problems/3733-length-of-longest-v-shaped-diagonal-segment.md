# 3733. Length Of Longest V Shaped Diagonal Segment

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3733-length-of-longest-v-shaped-diagonal-segment){ .md-button }

---

<h2><a href="https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment">3733. Length of Longest V-Shaped Diagonal Segment</a></h2><h3>Hard</h3><hr><p>You are given a 2D integer matrix <code>grid</code> of size <code>n x m</code>, where each element is either <code>0</code>, <code>1</code>, or <code>2</code>.</p>

<p>A <strong>V-shaped diagonal segment</strong> is defined as:</p>

<ul>
	<li>The segment starts with <code>1</code>.</li>
	<li>The subsequent elements follow this infinite sequence: <code>2, 0, 2, 0, ...</code>.</li>
	<li>The segment:
	<ul>
		<li>Starts <strong>along</strong> a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).</li>
		<li>Continues the<strong> sequence</strong> in the same diagonal direction.</li>
		<li>Makes<strong> at most one clockwise 90-degree</strong><strong> turn</strong> to another diagonal direction while <strong>maintaining</strong> the sequence.</li>
	</ul>
	</li>
</ul>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/01/11/length_of_longest3.jpg" style="width: 481px; height: 202px;" /></p>

<p>Return the <strong>length</strong> of the <strong>longest</strong> <strong>V-shaped diagonal segment</strong>. If no valid segment <em>exists</em>, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/12/09/matrix_1-2.jpg" style="width: 201px; height: 192px;" /></p>

<p>The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: <code>(0,2) &rarr; (1,3) &rarr; (2,4)</code>, takes a <strong>90-degree clockwise turn</strong> at <code>(2,4)</code>, and continues as <code>(3,3) &rarr; (4,2)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2024/12/09/matrix_2.jpg" style="width: 201px; height: 201px;" /></strong></p>

<p>The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: <code>(2,3) &rarr; (3,2)</code>, takes a <strong>90-degree clockwise turn</strong> at <code>(3,2)</code>, and continues as <code>(2,1) &rarr; (1,0)</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2024/12/09/matrix_3.jpg" style="width: 201px; height: 201px;" /></strong></p>

<p>The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: <code>(0,0) &rarr; (1,1) &rarr; (2,2) &rarr; (3,3) &rarr; (4,4)</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: <code>(0,0)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code>, <code>1</code> or <code>2</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    int dir[][] = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    private int dp[][][][];
    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length, m = grid[0].length; 
        int maxi = 0;

        dp = new int[n + 1][m + 1][dir.length + 1][2];
        for (int current[][][] : dp) {
            for (int current1[][] : current) {
                for (int current2[] : current1) 
                    Arrays.fill(current2, -1);
            }
        } 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        maxi = Math.max(maxi, solve(i, j, k, 1, grid));
                    }
                }
            }
        }

        return maxi;
    }

    private int solve(int currRow, int currCol, int currDirIdx, int canRotate, int grid[][]) {
        if (currRow < 0 || currCol < 0 || currRow >= grid.length || currCol >= grid[0].length)
            return 0;

        if (dp[currRow][currCol][currDirIdx][canRotate] != -1)
            return dp[currRow][currCol][currDirIdx][canRotate];
    
        if (grid[currRow][currCol] == 1) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 1, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0)
                if (grid[nextRow][nextCol] == 2)
                    op1 += 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);
            
            return dp[currRow][currCol][currDirIdx][canRotate] = op1;
        }

        else if (grid[currRow][currCol] == 2) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 0, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0) {
                if (grid[nextRow][nextCol] == 0) 
                    op1 = 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);      
            }
            //or rotate it from here;
            if (canRotate == 1) {
                int nextDirIdx = (currDirIdx + 1) % 4;
                int newNextRow = currRow + dir[nextDirIdx][0], newNextCol = currCol + dir[nextDirIdx][1];
                if (newNextRow >= 0 && newNextRow < grid.length && newNextCol >= 0 && newNextCol < grid[0].length) {
                    if (grid[newNextRow][newNextCol] == 0) {
                        op2 = 1 + solve(newNextRow, newNextCol, nextDirIdx, 0, grid);
                    }
                }
            }
            return dp[currRow][currCol][currDirIdx][canRotate] = Math.max(op1, op2);
        }
        
        else if (grid[currRow][currCol] == 0) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 0, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0) {
                if (grid[nextRow][nextCol] == 2)
                    op1 = 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);      
            }
            //or rotate it from here;
            if (canRotate == 1) {
                int nextDirIdx = (currDirIdx + 1) % 4;
                int newNextRow = currRow + dir[nextDirIdx][0], newNextCol = currCol + dir[nextDirIdx][1];
                if (newNextRow >= 0 && newNextRow < grid.length && newNextCol >= 0 && newNextCol < grid[0].length) {
                    if (grid[newNextRow][newNextCol] == 2) {
                        op2 = 1 + solve(newNextRow, newNextCol, nextDirIdx, 0, grid);
                    }
                }
            }  
            return dp[currRow][currCol][currDirIdx][canRotate] = Math.max(op1, op2); 
        }
        return 0;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

