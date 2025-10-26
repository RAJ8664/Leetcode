# 1859. Change Minimum Characters To Satisfy One Of Three Conditions

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1859-change-minimum-characters-to-satisfy-one-of-three-conditions){ .md-button }

---

<h2><a href="https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions">1859. Change Minimum Characters to Satisfy One of Three Conditions</a></h2><h3>Medium</h3><hr><p>You are given two strings <code>a</code> and <code>b</code> that consist of lowercase letters. In one operation, you can change any character in <code>a</code> or <code>b</code> to <strong>any lowercase letter</strong>.</p>

<p>Your goal is to satisfy <strong>one</strong> of the following three conditions:</p>

<ul>
	<li><strong>Every</strong> letter in <code>a</code> is <strong>strictly less</strong> than <strong>every</strong> letter in <code>b</code> in the alphabet.</li>
	<li><strong>Every</strong> letter in <code>b</code> is <strong>strictly less</strong> than <strong>every</strong> letter in <code>a</code> in the alphabet.</li>
	<li><strong>Both</strong> <code>a</code> and <code>b</code> consist of <strong>only one</strong> distinct letter.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations needed to achieve your goal.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aba&quot;, b = &quot;caa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Consider the best way to make each condition true:
1) Change b to &quot;ccc&quot; in 2 operations, then every letter in a is less than every letter in b.
2) Change a to &quot;bbb&quot; and b to &quot;aaa&quot; in 3 operations, then every letter in b is less than every letter in a.
3) Change a to &quot;aaa&quot; and b to &quot;aaa&quot; in 2 operations, then a and b consist of one distinct letter.
The best way was done in 2 operations (either condition 1 or condition 3).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;dabadd&quot;, b = &quot;cda&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The best way is to make condition 1 true by changing b to &quot;eee&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a</code> and <code>b</code> consist only of lowercase letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minCharacters(String a, String b) {
        int n = a.length(), m = b.length();

        int op1 = calcDistinct(a, b);

        int freq1[] = new int[26], freq2[] = new int[26];
        for (int i = 0; i < n; i++)
            freq1[a.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++)
            freq2[b.charAt(i) - 'a']++;

        int cx = 0, cy = 0;
        for (int i = 0; i < 25; i++) {
            cx += freq1[i];
            cy += freq2[i];
            op1 = Math.min(op1, Math.min(n - cx + cy, m - cy + cx));
        }
        return op1;
    }
    private int calcDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int freq1[] = new int[26], freq2[] = new int[26];
        for (int i = 0; i < n; i++)
            freq1[s.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++)
            freq2[t.charAt(i) - 'a']++;
        int maxi1 = 0, maxi2 = 0;
        for (int i = 0; i < 26; i++) {
            maxi1 = Math.max(maxi1, freq1[i]);
            maxi2 = Math.max(maxi2, freq2[i]);
        }
        return n - maxi1 + m - maxi2;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

