# 3786. Longest Palindromic Subsequence After At Most K Operations


---

<h2><a href="https://leetcode.com/problems/longest-palindromic-subsequence-after-at-most-k-operations">3786. Longest Palindromic Subsequence After at Most K Operations</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>In one operation, you can replace the character at any position with the next or previous letter in the alphabet (wrapping around so that <code>&#39;a&#39;</code> is after <code>&#39;z&#39;</code>). For example, replacing <code>&#39;a&#39;</code> with the next letter results in <code>&#39;b&#39;</code>, and replacing <code>&#39;a&#39;</code> with the previous letter results in <code>&#39;z&#39;</code>. Similarly, replacing <code>&#39;z&#39;</code> with the next letter results in <code>&#39;a&#39;</code>, and replacing <code>&#39;z&#39;</code> with the previous letter results in <code>&#39;y&#39;</code>.</p>

<p>Return the length of the <strong>longest <span data-keyword="palindrome-string">palindromic</span> <span data-keyword="subsequence-string-nonempty">subsequence</span></strong> of <code>s</code> that can be obtained after performing <strong>at most</strong> <code>k</code> operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abced&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[1]</code> with the next letter, and <code>s</code> becomes <code>&quot;acced&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the previous letter, and <code>s</code> becomes <code>&quot;accec&quot;</code>.</li>
</ul>

<p>The subsequence <code>&quot;ccc&quot;</code> forms a palindrome of length 3, which is the maximum.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;</span>aaazzz<span class="example-io">&quot;, k = 4</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[0]</code> with the previous letter, and <code>s</code> becomes <code>&quot;zaazzz&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaazaz&quot;</code>.</li>
	<li>Replace <code>s[3]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaaaaz&quot;</code>.</li>
</ul>

<p>The entire string forms a palindrome of length 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>


## Solution

```java
class Solution {
    static public int longestPalindromicSubsequence(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        Integer[][][] dp = new Integer[k + 1][n][n];
        return solve(0, n - 1, k, arr, dp);
    }
    static int solve(int i, int j, int k, char[] arr, Integer[][][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        if(dp[k][i][j] != null) return dp[k][i][j];
        int a = -1000000, b = -1000000, c = -1000000, d = -1000000;
        if (arr[i] == arr[j]) a = 2 + solve(i + 1, j - 1, k, arr, dp);
        else {
            b = solve(i + 1, j, k, arr, dp);
            c = solve(i, j - 1, k, arr, dp);
            int x = (int) (arr[i] - 97);
            int y = (int) (arr[j] - 97);
            int first = Math.abs(x - y);
            x += 26;
            int second = Math.abs(x - y);
            x -= 26;
            y += 26;
            int third = Math.abs(x - y);
            int min = Math.min(first, Math.min(second, third));
            if(k >= min) d = 2 + solve(i + 1, j - 1, k - min, arr, dp);
        }
        return dp[k][i][j] =  Math.max(a, Math.max(b, Math.max(c, d)));
    }
}

```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

