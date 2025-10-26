# 878. Shifting Letters

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/shifting-letters/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0878-shifting-letters){ .md-button }

---

<h2><a href="https://leetcode.com/problems/shifting-letters">878. Shifting Letters</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> of lowercase English letters and an integer array <code>shifts</code> of the same length.</p>

<p>Call the <code>shift()</code> of a letter, the next letter in the alphabet, (wrapping around so that <code>&#39;z&#39;</code> becomes <code>&#39;a&#39;</code>).</p>

<ul>
	<li>For example, <code>shift(&#39;a&#39;) = &#39;b&#39;</code>, <code>shift(&#39;t&#39;) = &#39;u&#39;</code>, and <code>shift(&#39;z&#39;) = &#39;a&#39;</code>.</li>
</ul>

<p>Now for each <code>shifts[i] = x</code>, we want to shift the first <code>i + 1</code> letters of <code>s</code>, <code>x</code> times.</p>

<p>Return <em>the final string after all such shifts to s are applied</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, shifts = [3,5,9]
<strong>Output:</strong> &quot;rpl&quot;
<strong>Explanation:</strong> We start with &quot;abc&quot;.
After shifting the first 1 letters of s by 3, we have &quot;dbc&quot;.
After shifting the first 2 letters of s by 5, we have &quot;igc&quot;.
After shifting the first 3 letters of s by 9, we have &quot;rpl&quot;, the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;, shifts = [1,2,3]
<strong>Output:</strong> &quot;gfd&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>shifts.length == s.length</code></li>
	<li><code>0 &lt;= shifts[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();
        long pref[] = new long[n];
        for (int i = 0; i < n; i++) {
            int u = 0, v = i;
            pref[u] = (pref[u] + shifts[i]);
            if (v + 1 < n) pref[v + 1] -= shifts[i];
        }
        for (int i = 1; i < n; i++) pref[i] += pref[i - 1];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (pref[i] == 0) {
                res.append(current);
                continue;
            }
            else {
                long time_forward = pref[i] % 26;
                while (time_forward > 0) {
                    if (current == 'z') {
                        current = 'a';
                        time_forward--;
                    }
                    else {
                        current++;
                        time_forward--;
                    }
                }
                res.append(current);
            }
        }
        return res.toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

