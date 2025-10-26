# 1678. Number Of Ways To Split A String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-ways-to-split-a-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1678-number-of-ways-to-split-a-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-ways-to-split-a-string">1678. Number of Ways to Split a String</a></h2><h3>Medium</h3><hr><p>Given a binary string <code>s</code>, you can split <code>s</code> into 3 <strong>non-empty</strong> strings <code>s1</code>, <code>s2</code>, and <code>s3</code> where <code>s1 + s2 + s3 = s</code>.</p>

<p>Return the number of ways <code>s</code> can be split such that the number of ones is the same in <code>s1</code>, <code>s2</code>, and <code>s3</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10101&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four ways to split s in 3 parts where each part contain the same number of letters &#39;1&#39;.
&quot;1|010|1&quot;
&quot;1|01|01&quot;
&quot;10|10|1&quot;
&quot;10|1|01&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three ways to split s in 3 parts.
&quot;0|0|00&quot;
&quot;0|00|0&quot;
&quot;00|0|0&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    private int pref[];
    private int mod = 1000000007;
    public int numWays(String s) {
        int n = s.length();
        pref = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                pref[i] = 1;
            if (i - 1 >= 0)
                pref[i] += pref[i - 1];
        }

        long ways = 0;
        for (int i = 0; i < n - 2; i++) {
            int leftOnes = pref[i];

            int left = bsLeft(i + 1, n - 2, pref[i]);
            int right = bsRight(i + 1, n - 2, pref[i]);

            if (left == -1 || right == -1)
                continue;
            int rightOnes = pref[n - 1] - pref[right];

            if (rightOnes == leftOnes)
                ways = (ways + right - left + 1) % mod;
        }
        return (int)(ways);
    }

    private int bsLeft(int start, int end, int req) {
        int low = start, high = end;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int total = pref[mid];
            if (start - 1 >= 0)
                total -= pref[start - 1];
            if (total > req)
                high = mid - 1;
            else if (total == req) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private int bsRight(int start, int end, int req) {
        int low = start, high = end, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int total = pref[mid];
            if (start - 1 >= 0)
                total -= pref[start - 1];
            if (total > req)
                high = mid - 1;
            else if (total == req) {
                ans = mid;
                low = mid + 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

