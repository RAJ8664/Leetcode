# 1502. Construct K Palindrome Strings

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/construct-k-palindrome-strings/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1502-construct-k-palindrome-strings){ .md-button }

---

<h2><a href="https://leetcode.com/problems/construct-k-palindrome-strings">1502. Construct K Palindrome Strings</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code> and an integer <code>k</code>, return <code>true</code> <em>if you can use all the characters in </em><code>s</code><em> to construct </em><code>k</code><em> palindrome strings or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;annabelle&quot;, k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> You can construct two palindromes using all characters in s.
Some possible constructions &quot;anna&quot; + &quot;elble&quot;, &quot;anbna&quot; + &quot;elle&quot;, &quot;anellena&quot; + &quot;b&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to construct 3 palindromes using all the characters of s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;true&quot;, k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> The only possible solution is to put each character in a separate string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length(), count = 0;
        if (n < k) return false;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) if (freq[i] % 2 == 1) count++;
        return count <= k;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

