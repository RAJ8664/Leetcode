# 3849. Equal Sum Grid Partition I


---

<h2><a href="https://leetcode.com/problems/equal-sum-grid-partition-i">3849. Equal Sum Grid Partition I</a></h2><h3>Medium</h3><hr><p>You are given an <code>m x n</code> matrix <code>grid</code> of positive integers. Your task is to determine if it is possible to make <strong>either one horizontal or one vertical cut</strong> on the grid such that:</p>

<ul>
	<li>Each of the two resulting sections formed by the cut is <strong>non-empty</strong>.</li>
	<li>The sum of the elements in both sections is <strong>equal</strong>.</li>
</ul>

<p>Return <code>true</code> if such a partition exists; otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,4],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/03/30/lc.png" style="width: 200px;" /><img alt="" src="https://assets.leetcode.com/uploads/2025/03/30/lc.jpeg" style="width: 200px; height: 200px;" /></p>

<p>A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solution

```java
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int row_sum[] = new int[n];
        int col_sum[] = new int[m];
        int sum = 0;
        for (int i = 0;  i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += grid[i][j];
            }
            row_sum[i] = sum;
        }
        sum = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                sum += grid[i][j];
            }
            col_sum[j] = sum;
        }
        for (int i = 0; i < n - 1; i++) {
            int up_sum = row_sum[i];
            int buttom_sum = row_sum[n - 1] - up_sum;
            if (up_sum == buttom_sum) return true;
        }
        for (int j = 0; j < m - 1; j++) {
            int left_sum = col_sum[j];
            int right_sum = col_sum[m - 1] - left_sum;
            if (left_sum == right_sum) return true;
        }    
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

