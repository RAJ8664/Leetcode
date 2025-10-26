# 3737. Paint House Iv


---

<h2><a href="https://leetcode.com/problems/paint-house-iv">3737. Paint House IV</a></h2><h3>Medium</h3><hr><p>You are given an <strong>even</strong> integer <code>n</code> representing the number of houses arranged in a straight line, and a 2D array <code>cost</code> of size <code>n x 3</code>, where <code>cost[i][j]</code> represents the cost of painting house <code>i</code> with color <code>j + 1</code>.</p>

<p>The houses will look <strong>beautiful</strong> if they satisfy the following conditions:</p>

<ul>
	<li>No <strong>two</strong> adjacent houses are painted the same color.</li>
	<li>Houses <strong>equidistant</strong> from the ends of the row are <strong>not</strong> painted the same color. For example, if <code>n = 6</code>, houses at positions <code>(0, 5)</code>, <code>(1, 4)</code>, and <code>(2, 3)</code> are considered equidistant.</li>
</ul>

<p>Return the <strong>minimum</strong> cost to paint the houses such that they look <strong>beautiful</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, cost = [[3,5,7],[6,2,9],[4,8,1],[7,3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal painting sequence is <code>[1, 2, 3, 2]</code> with corresponding costs <code>[3, 2, 1, 3]</code>. This satisfies the following conditions:</p>

<ul>
	<li>No adjacent houses have the same color.</li>
	<li>Houses at positions 0 and 3 (equidistant from the ends) are not painted the same color <code>(1 != 2)</code>.</li>
	<li>Houses at positions 1 and 2 (equidistant from the ends) are not painted the same color <code>(2 != 3)</code>.</li>
</ul>

<p>The minimum cost to paint the houses so that they look beautiful is <code>3 + 2 + 1 + 3 = 9</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, cost = [[2,4,6],[5,3,8],[7,1,9],[4,6,2],[3,5,7],[8,2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal painting sequence is <code>[1, 3, 2, 3, 1, 2]</code> with corresponding costs <code>[2, 8, 1, 2, 3, 2]</code>. This satisfies the following conditions:</p>

<ul>
	<li>No adjacent houses have the same color.</li>
	<li>Houses at positions 0 and 5 (equidistant from the ends) are not painted the same color <code>(1 != 2)</code>.</li>
	<li>Houses at positions 1 and 4 (equidistant from the ends) are not painted the same color <code>(3 != 1)</code>.</li>
	<li>Houses at positions 2 and 3 (equidistant from the ends) are not painted the same color <code>(2 != 3)</code>.</li>
</ul>

<p>The minimum cost to paint the houses so that they look beautiful is <code>2 + 8 + 1 + 2 + 3 + 2 = 18</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>cost.length == n</code></li>
	<li><code>cost[i].length == 3</code></li>
	<li><code>0 &lt;= cost[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solution

```java
class Solution {
    private long dp[][][][];
    public long minCost(int n, int[][] cost) {
        dp = new long[n + 1][4][4][3];
        for (long current[][][] : dp) for (long current1[][] : current) for (long current2[] : current1) Arrays.fill(current2, -1);
        return solve(n, 0, cost, 3, 3, 0);   
    }
    private long solve(int n, int idx, int cost[][], int prev1, int prev2, int start) {
        if (idx >= n / 2) return 0;
        long ans = Long.MAX_VALUE;
        if (dp[idx][prev1][prev2][start] != - 1) return dp[idx][prev1][prev2][start];
        if (start == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        long ans1 = (long) cost[idx][i] + (long) cost[n - 1 - idx][j] + solve(n, idx + 1, cost, i, j, 1);
                        ans = Math.min(ans, ans1);
                    }
                }
            }
        } 
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (prev1 != i && prev2 != j && i != j) {
                        long ans1 = (long) cost[idx][i] + (long) cost[n-1-idx][j] + solve(n, idx + 1, cost, i, j, start);
                        ans = Math.min(ans, ans1);
                    }
                }
            }
        }
        return dp[idx][prev1][prev2][start] = ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

