# 2145. Grid Game

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/grid-game/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2145-grid-game){ .md-button }

---

<h2><a href="https://leetcode.com/problems/grid-game">2145. Grid Game</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> 2D array <code>grid</code> of size <code>2 x n</code>, where <code>grid[r][c]</code> represents the number of points at position <code>(r, c)</code> on the matrix. Two robots are playing a game on this matrix.</p>

<p>Both robots initially start at <code>(0, 0)</code> and want to reach <code>(1, n-1)</code>. Each robot may only move to the <strong>right</strong> (<code>(r, c)</code> to <code>(r, c + 1)</code>) or <strong>down </strong>(<code>(r, c)</code> to <code>(r + 1, c)</code>).</p>

<p>At the start of the game, the <strong>first</strong> robot moves from <code>(0, 0)</code> to <code>(1, n-1)</code>, collecting all the points from the cells on its path. For all cells <code>(r, c)</code> traversed on the path, <code>grid[r][c]</code> is set to <code>0</code>. Then, the <strong>second</strong> robot moves from <code>(0, 0)</code> to <code>(1, n-1)</code>, collecting the points on its path. Note that their paths may intersect with one another.</p>

<p>The <strong>first</strong> robot wants to <strong>minimize</strong> the number of points collected by the <strong>second</strong> robot. In contrast, the <strong>second </strong>robot wants to <strong>maximize</strong> the number of points it collects. If both robots play <strong>optimally</strong>, return <em>the <b>number of points</b> collected by the <strong>second</strong> robot.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/09/08/a1.png" style="width: 388px; height: 103px;" />
<pre>
<strong>Input:</strong> grid = [[2,5,4],[1,5,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 0 + 4 + 0 = 4 points.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/09/08/a2.png" style="width: 384px; height: 105px;" />
<pre>
<strong>Input:</strong> grid = [[3,3,1],[8,5,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 3 + 1 + 0 = 4 points.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/09/08/a3.png" style="width: 493px; height: 103px;" />
<pre>
<strong>Input:</strong> grid = [[1,3,1,15],[1,3,3,1]]
<strong>Output:</strong> 7
<strong>Explanation: </strong>The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == 2</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long gridGame(int[][] grid) {
        int m = grid[0].length;
        long[] pref1 = new long[m];
        long[] pref2 = new long[m];
        pref1[0] = grid[0][0];
        pref2[0] = grid[1][0];
        for (int i = 1; i < m; i++) {
            pref1[i] = pref1[i - 1] + grid[0][i];
            pref2[i] = pref2[i - 1] + grid[1][i];
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            long first = 0, second = 0;
            if (i != m - 1) first = pref1[m - 1] - pref1[i];
            if (i != 0) second = pref2[i - 1];
            res = Math.min(res, Math.max(first, second));
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

