# 2311. Minimum White Tiles After Covering With Carpets

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2311-minimum-white-tiles-after-covering-with-carpets){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets">2311. Minimum White Tiles After Covering With Carpets</a></h2><h3>Hard</h3><hr><p>You are given a <strong>0-indexed binary</strong> string <code>floor</code>, which represents the colors of tiles on a floor:</p>

<ul>
	<li><code>floor[i] = &#39;0&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>black</strong>.</li>
	<li>On the other hand, <code>floor[i] = &#39;1&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>white</strong>.</li>
</ul>

<p>You are also given <code>numCarpets</code> and <code>carpetLen</code>. You have <code>numCarpets</code> <strong>black</strong> carpets, each of length <code>carpetLen</code> tiles. Cover the tiles with the given carpets such that the number of <strong>white</strong> tiles still visible is <strong>minimum</strong>. Carpets may overlap one another.</p>

<p>Return <em>the <strong>minimum</strong> number of white tiles still visible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/02/10/ex1-1.png" style="width: 400px; height: 73px;" />
<pre>
<strong>Input:</strong> floor = &quot;10110101&quot;, numCarpets = 2, carpetLen = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/02/10/ex2.png" style="width: 353px; height: 123px;" />
<pre>
<strong>Input:</strong> floor = &quot;11111&quot;, numCarpets = 2, carpetLen = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
Note that the carpets are able to overlap one another.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= carpetLen &lt;= floor.length &lt;= 1000</code></li>
	<li><code>floor[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= numCarpets &lt;= 1000</code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int dp[][];
    private int suff[];
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        suff = new int[n];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (floor.charAt(i) == '1')
                count++;
            suff[i] = count;
        }

        dp = new int[n + 1][numCarpets + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(0, numCarpets, floor, carpetLen);
    }

    private int solve(int ind, int carpetsLeft, String floor, int carpetLen) {
        if (ind >= floor.length())
            return 0;
        if (carpetsLeft == 0)
            return suff[ind];

        if (dp[ind][carpetsLeft] != -1)
            return dp[ind][carpetsLeft];

        int op1 = solve(ind + 1, carpetsLeft, floor, carpetLen) + (floor.charAt(ind) == '1' ? 1 : 0);
        int op2 = solve(ind + carpetLen, carpetsLeft - 1, floor, carpetLen);

        return dp[ind][carpetsLeft] = Math.min(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

