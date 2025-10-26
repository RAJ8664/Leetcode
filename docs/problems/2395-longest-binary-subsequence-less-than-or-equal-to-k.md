# 2395. Longest Binary Subsequence Less Than Or Equal To K

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2395-longest-binary-subsequence-less-than-or-equal-to-k){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k">2395. Longest Binary Subsequence Less Than or Equal to K</a></h2><h3>Medium</h3><hr><p>You are given a binary string <code>s</code> and a positive integer <code>k</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> subsequence of </em><code>s</code><em> that makes up a <strong>binary</strong> number less than or equal to</em> <code>k</code>.</p>

<p>Note:</p>

<ul>
	<li>The subsequence can contain <strong>leading zeroes</strong>.</li>
	<li>The empty string is considered to be equal to <code>0</code>.</li>
	<li>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001010&quot;, k = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> The longest subsequence of s that makes up a binary number less than or equal to 5 is &quot;00010&quot;, as this number is equal to 2 in decimal.
Note that &quot;00100&quot; and &quot;00101&quot; are also possible, which are equal to 4 and 5 in decimal, respectively.
The length of this subsequence is 5, so 5 is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00101001&quot;, k = 1
<strong>Output:</strong> 6
<strong>Explanation:</strong> &quot;000001&quot; is the longest subsequence of s that makes up a binary number less than or equal to 1, as this number is equal to 1 in decimal.
The length of this subsequence is 6, so 6 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int dp[][];
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        dp = new int[n + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(n - 1, 0, 0, k, s);
    }

    private int solve(int ind, long val, int pos, int k, String s) {
        if (ind < 0)
            return 0;
        if (dp[ind][pos] != -1)
            return dp[ind][pos];

        int op1 = 0, op2 = 0;
        op1 = solve(ind - 1, val, pos, k, s);
        if (s.charAt(ind) == '0')
            op2 = 1 + solve(ind - 1, val, pos + 1, k, s);
        else if (s.charAt(ind) == '1') {
            if (pos < 33 && (val + (1L << pos)) <= k)
                op2 = 1 + solve(ind - 1, val + (1L << pos), pos + 1, k, s);
        }
        return dp[ind][pos] = Math.max(op2, op1);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

