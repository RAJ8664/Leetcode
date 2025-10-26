# 1351. Replace The Substring For Balanced String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/replace-the-substring-for-balanced-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1351-replace-the-substring-for-balanced-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/replace-the-substring-for-balanced-string">1351. Replace the Substring for Balanced String</a></h2><h3>Medium</h3><hr><p>You are given a string s of length <code>n</code> containing only four kinds of characters: <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</p>

<p>A string is said to be <strong>balanced</strong><em> </em>if each of its characters appears <code>n / 4</code> times where <code>n</code> is the length of the string.</p>

<p>Return <em>the minimum length of the substring that can be replaced with <strong>any</strong> other string of the same length to make </em><code>s</code><em> <strong>balanced</strong></em>. If s is already <strong>balanced</strong>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QWER&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> s is already balanced.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQWE&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We need to replace a &#39;Q&#39; to &#39;R&#39;, so that &quot;RQWE&quot; (or &quot;QRWE&quot;) is balanced.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQQW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can replace the first &quot;QQ&quot; to &quot;ER&quot;. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is a multiple of <code>4</code>.</li>
	<li><code>s</code> contains only <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int balancedString(String s) {
        int[] count = new int[128];
        char[] arr = s.toCharArray();
        for (char c: arr) count[c]++;
        int need = arr.length / 4;
        int left = 0, right = 0, min = arr.length;
        while (right <= arr.length) {
            if (count['Q'] > need || count['W'] > need || count['E'] > need || count['R'] > need) {
				if (right >= arr.length) break;
                char rightCh = arr[right];
                count[rightCh]--;
                right++;
                continue;
            }
            min = Math.min(min, right-left);
            if (min == 0) break;
            char leftCh = arr[left];
            count[leftCh]++;
            left++;
        }
        return min;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

