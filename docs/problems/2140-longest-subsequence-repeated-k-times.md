# 2140. Longest Subsequence Repeated K Times

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-subsequence-repeated-k-times/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2140-longest-subsequence-repeated-k-times){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-subsequence-repeated-k-times">2140. Longest Subsequence Repeated k Times</a></h2><h3>Hard</h3><hr><p>You are given a string <code>s</code> of length <code>n</code>, and an integer <code>k</code>. You are tasked to find the <strong>longest subsequence repeated</strong> <code>k</code> times in string <code>s</code>.</p>

<p>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p>A subsequence <code>seq</code> is <strong>repeated</strong> <code>k</code> times in the string <code>s</code> if <code>seq * k</code> is a subsequence of <code>s</code>, where <code>seq * k</code> represents a string constructed by concatenating <code>seq</code> <code>k</code> times.</p>

<ul>
	<li>For example, <code>&quot;bba&quot;</code> is repeated <code>2</code> times in the string <code>&quot;bababcba&quot;</code>, because the string <code>&quot;bbabba&quot;</code>, constructed by concatenating <code>&quot;bba&quot;</code> <code>2</code> times, is a subsequence of the string <code>&quot;<strong><u>b</u></strong>a<strong><u>bab</u></strong>c<strong><u>ba</u></strong>&quot;</code>.</li>
</ul>

<p>Return <em>the <strong>longest subsequence repeated</strong> </em><code>k</code><em> times in string </em><code>s</code><em>. If multiple such subsequences are found, return the <strong>lexicographically largest</strong> one. If there is no such subsequence, return an <strong>empty</strong> string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example 1" src="https://assets.leetcode.com/uploads/2021/08/30/longest-subsequence-repeat-k-times.png" style="width: 457px; height: 99px;" />
<pre>
<strong>Input:</strong> s = &quot;letsleetcode&quot;, k = 2
<strong>Output:</strong> &quot;let&quot;
<strong>Explanation:</strong> There are two longest subsequences repeated 2 times: &quot;let&quot; and &quot;ete&quot;.
&quot;let&quot; is the lexicographically largest one.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bb&quot;, k = 2
<strong>Output:</strong> &quot;b&quot;
<strong>Explanation:</strong> The longest subsequence repeated 2 times is &quot;b&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab&quot;, k = 2
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no subsequence repeated 2 times. Empty string is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n, k &lt;= 2000</code></li>
	<li><code>2 &lt;= n &lt; k * 8</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int freq[] = new int[26];
        for (int i = 0; i < n; i++)
            freq[s.charAt(i) - 'a']++;

        ArrayList<Character> validCharacters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k)
                validCharacters.add((char)(i + 'a'));
        }

        String ans = "";
        Queue<String> q = new LinkedList<>();
        q.offer("");
        while (q.size() > 0) {
            String curr = q.poll();
            for (char c : validCharacters) {
                String child = curr + c;
                if (isValid(s, child, k)) {
                    ans = child;
                    q.offer(child);
                }
            }
        }
        return ans;
    }
    private boolean isValid(String s, String temp, int k) {
        int n = s.length();
        String t = "";
        for (int i = 0; i < k; i++)
            t += temp;
        int m = t.length();

        if (m > n)
            return false;

        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else
                i++;
        }
        return j == m;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

