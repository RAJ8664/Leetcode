# 120. Triangle

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/triangle/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0120-triangle){ .md-button }

---

<h2><a href="https://leetcode.com/problems/triangle">120. Triangle</a></h2><h3>Medium</h3><hr><p>Given a <code>triangle</code> array, return <em>the minimum path sum from top to bottom</em>.</p>

<p>For each step, you may move to an adjacent number of the row below. More formally, if you are on index <code>i</code> on the current row, you may move to either index <code>i</code> or index <code>i + 1</code> on the next row.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The triangle looks like:
   <u>2</u>
  <u>3</u> 4
 6 <u>5</u> 7
4 <u>1</u> 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> triangle = [[-10]]
<strong>Output:</strong> -10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= triangle.length &lt;= 200</code></li>
	<li><code>triangle[0].length == 1</code></li>
	<li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you&nbsp;do this using only <code>O(n)</code> extra space, where <code>n</code> is the total number of rows in the triangle?

---

## Solution

```java
class Solution {
    private int dp[][];
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        dp = new int[n + 1][triangle.get(n - 1).size() + 1];
        for (int current[] : dp) 
            Arrays.fill(current, -(int)(1e9));
        return solve(0, 0, triangle);
    }
    private int solve(int row, int col, List<List<Integer>> triangle) {
        if (row >= triangle.size())
            return 0;

        if (col >= triangle.get(row).size()) {
            return Integer.MAX_VALUE / 10;
        }
        
        if (dp[row][col] != -(int)(1e9))
            return dp[row][col];
        
        int op1 = triangle.get(row).get(col) + solve(row + 1, col, triangle);
        int op2 = triangle.get(row).get(col) + solve(row + 1, col + 1, triangle);       

        return dp[row][col] = Math.min(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

