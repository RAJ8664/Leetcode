# 3018. Make String A Subsequence Using Cyclic Increments

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3018-make-string-a-subsequence-using-cyclic-increments){ .md-button }

---

<h2><a href="https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments">3018. Make String a Subsequence Using Cyclic Increments</a></h2><h3>Medium</h3><hr><p>You are given two <strong>0-indexed</strong> strings <code>str1</code> and <code>str2</code>.</p>

<p>In an operation, you select a <strong>set</strong> of indices in <code>str1</code>, and for each index <code>i</code> in the set, increment <code>str1[i]</code> to the next character <strong>cyclically</strong>. That is <code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code>, <code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code>, and so on, and <code>&#39;z&#39;</code> becomes <code>&#39;a&#39;</code>.</p>

<p>Return <code>true</code> <em>if it is possible to make </em><code>str2</code> <em>a subsequence of </em><code>str1</code> <em>by performing the operation <strong>at most once</strong></em>, <em>and</em> <code>false</code> <em>otherwise</em>.</p>

<p><strong>Note:</strong> A subsequence of a string is a new string that is formed from the original string by deleting some (possibly none) of the characters without disturbing the relative positions of the remaining characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;abc&quot;, str2 = &quot;ad&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Select index 2 in str1.
Increment str1[2] to become &#39;d&#39;. 
Hence, str1 becomes &quot;abd&quot; and str2 is now a subsequence. Therefore, true is returned.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;zc&quot;, str2 = &quot;ad&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Select indices 0 and 1 in str1. 
Increment str1[0] to become &#39;a&#39;. 
Increment str1[1] to become &#39;d&#39;. 
Hence, str1 becomes &quot;ad&quot; and str2 is now a subsequence. Therefore, true is returned.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;ab&quot;, str2 = &quot;d&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> In this example, it can be shown that it is impossible to make str2 a subsequence of str1 using the operation at most once. 
Therefore, false is returned.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= str1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= str2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>str1</code> and <code>str2</code> consist of only lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int j = 0;
        for(int i = 0; i < m && j < n; i++){
            int a = str1.charAt(i);
            int b = str2.charAt(j);
            if(a == b || a + 1 == b || a - 25 == b) j++;
        }
        return j == n;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

