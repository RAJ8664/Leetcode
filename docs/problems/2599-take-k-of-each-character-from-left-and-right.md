# 2599. Take K Of Each Character From Left And Right

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2599-take-k-of-each-character-from-left-and-right){ .md-button }

---

<h2><a href="https://leetcode.com/problems/take-k-of-each-character-from-left-and-right">2599. Take K of Each Character From Left and Right</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> consisting of the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code> and a non-negative integer <code>k</code>. Each minute, you may take either the <strong>leftmost</strong> character of <code>s</code>, or the <strong>rightmost</strong> character of <code>s</code>.</p>

<p>Return<em> the <strong>minimum</strong> number of minutes needed for you to take <strong>at least</strong> </em><code>k</code><em> of each character, or return </em><code>-1</code><em> if it is not possible to take </em><code>k</code><em> of each character.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaaaacaabc&quot;, k = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
Take three characters from the left of s. You now have two &#39;a&#39; characters, and one &#39;b&#39; character.
Take five characters from the right of s. You now have four &#39;a&#39; characters, two &#39;b&#39; characters, and two &#39;c&#39; characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to take one &#39;b&#39; or &#39;c&#39; so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only the letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int pref[][];
    private int suff[][];
    public int takeCharacters(String s, int k) {
        int n = s.length();
        if (k == 0) return 0;
        pref = new int[n][3];
        suff = new int[n][3];
        int counta = 0, countb = 0, countc = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') counta++;
            else if (s.charAt(i) == 'b') countb++;
            else countc++;
            pref[i][0] = counta; pref[i][1] = countb; pref[i][2] = countc;
        }
        counta = 0; countb = 0; countc = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') counta++;
            else if (s.charAt(i) == 'b') countb++;
            else countc++;
            suff[i][0] = counta; suff[i][1] = countb; suff[i][2] = countc;
        }
        if (pref[n - 1][0] < k || pref[n - 1][1] < k || pref[n - 1][2] < k) return -1; 
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int curr_counta = pref[i][0], curr_countb = pref[i][1], curr_countc = pref[i][2];
            if (curr_counta >= k && curr_countb >= k && curr_countc >= k) {
                mini = Math.min(mini, i + 1);
                continue;
            }
            int ind = binary_search(i + 1, n - 1, n, k);
            if (ind == -1) continue;
            mini = Math.min(mini, (i + 1 + (n  - ind)));
        }

        for (int i = n - 1; i >= 0; i--) {
            int curr_counta = suff[i][0], curr_countb = suff[i][1], curr_countc = suff[i][2];
            if (curr_counta >= k && curr_countb >= k && curr_countc >= k) {
                mini = Math.min(mini, (n - i));
                continue;
            }
            if (i - 1 >= 0) {
                int ind = binary_search1(0, i - 1, n , k);
                if (ind != -1) mini = Math.min(mini, (n - i + 1 + ind + 1)); 
            }
        }
        return mini;
    }
    private int binary_search(int low, int high, int n, int k) {
        int counta = 0, countb = 0, countc = 0;
        counta = pref[low - 1][0]; countb = pref[low - 1][1]; countc = pref[low - 1][2];
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int curr_counta = suff[mid][0], curr_countb = suff[mid][1], curr_countc = suff[mid][2];
            if (counta + curr_counta >= k && countb + curr_countb >= k && countc + curr_countc >= k) {
                ans = mid;
                low = mid + 1;
            } 
            else high = mid - 1;
        }
        return ans;
    }

    private int binary_search1(int low, int high, int n, int k) {
        int counta = suff[high + 1][0], countb = suff[high + 1][1], countc = suff[high + 1][2];
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int curr_counta = pref[mid][0], curr_countb = pref[mid][1], curr_countc = pref[mid][2];
            if (counta + curr_counta >= k && countb + curr_countb >= k && countc + curr_countc >= k) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
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

