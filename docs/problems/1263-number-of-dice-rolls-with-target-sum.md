# 1263. Number Of Dice Rolls With Target Sum

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1263-number-of-dice-rolls-with-target-sum){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-dice-rolls-with-target-sum">1263. Number of Dice Rolls With Target Sum</a></h2><h3>Medium</h3><hr><p>You have <code>n</code> dice, and each dice has <code>k</code> faces numbered from <code>1</code> to <code>k</code>.</p>

<p>Given three integers <code>n</code>, <code>k</code>, and <code>target</code>, return <em>the number of possible ways (out of the </em><code>k<sup>n</sup></code><em> total ways) </em><em>to roll the dice, so the sum of the face-up numbers equals </em><code>target</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 6, target = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> You throw one die with 6 faces.
There is only one way to get a sum of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 6, target = 7
<strong>Output:</strong> 6
<strong>Explanation:</strong> You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 30, k = 30, target = 500
<strong>Output:</strong> 222616187
<strong>Explanation:</strong> The answer must be returned modulo 10<sup>9</sup> + 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 30</code></li>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int dp[][];
    private int mod = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        dp = new int[n + 1][target + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(n, 0, target, k) % mod;
    }
    private int solve(int n, int ind, int target, int k) {
        if (ind >= n) {
            if (target != 0)
                return 0;
            return 1;
        }

        if (dp[ind][target] != -1)
            return dp[ind][target] % mod;

        long ways = 0;
        for (int i = 1; i <= k; i++) {
            if (target >= i)
                ways = (ways + solve(n, ind + 1, target - i, k)) % mod;
        }
        return dp[ind][target] = (int)(ways % mod);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

