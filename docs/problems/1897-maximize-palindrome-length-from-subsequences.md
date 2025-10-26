# 1897. Maximize Palindrome Length From Subsequences

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1897-maximize-palindrome-length-from-subsequences){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximize-palindrome-length-from-subsequences">1897. Maximize Palindrome Length From Subsequences</a></h2><h3>Hard</h3><hr><p>You are given two strings, <code>word1</code> and <code>word2</code>. You want to construct a string in the following manner:</p>

<ul>
	<li>Choose some <strong>non-empty</strong> subsequence <code>subsequence1</code> from <code>word1</code>.</li>
	<li>Choose some <strong>non-empty</strong> subsequence <code>subsequence2</code> from <code>word2</code>.</li>
	<li>Concatenate the subsequences: <code>subsequence1 + subsequence2</code>, to make the string.</li>
</ul>

<p>Return <em>the <strong>length</strong> of the longest <strong>palindrome</strong> that can be constructed in the described manner. </em>If no palindromes can be constructed, return <code>0</code>.</p>

<p>A <strong>subsequence</strong> of a string <code>s</code> is a string that can be made by deleting some (possibly none) characters from <code>s</code> without changing the order of the remaining characters.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forward&nbsp;as well as backward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;cacb&quot;, word2 = &quot;cbba&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Choose &quot;ab&quot; from word1 and &quot;cba&quot; from word2 to make &quot;abcba&quot;, which is a palindrome.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;ab&quot;, word2 = &quot;ab&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Choose &quot;ab&quot; from word1 and &quot;a&quot; from word2 to make &quot;aba&quot;, which is a palindrome.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;aa&quot;, word2 = &quot;bb&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> You cannot construct a palindrome from the described method, so return 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 1000</code></li>
	<li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int[][] dp;

    public int longestPalindrome(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        String s = word1 + word2;
        dp = new int[n + m][n + m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        fillDp(0, n + m - 1, s);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    res = Math.max(res, dp[i][n + j]);
            }
        }
        return res;
    }

    private int fillDp(int i, int j, String s) {
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = (i == j ? 1 : 2) + fillDp(i + 1, j - 1, s);
        return dp[i][j] = Math.max(fillDp(i + 1, j, s), fillDp(i, j - 1, s));
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

