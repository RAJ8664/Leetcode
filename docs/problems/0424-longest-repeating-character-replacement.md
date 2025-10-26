# 424. Longest Repeating Character Replacement

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-repeating-character-replacement/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0424-longest-repeating-character-replacement){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-repeating-character-replacement">424. Longest Repeating Character Replacement</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most <code>k</code> times.</p>

<p>Return <em>the length of the longest substring containing the same letter you can get after performing the above operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABAB&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the two &#39;A&#39;s with two &#39;B&#39;s or vice versa.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AABABBA&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the one &#39;A&#39; in the middle with &#39;B&#39; and form &quot;AABBBBA&quot;.
The substring &quot;BBBB&quot; has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only uppercase English letters.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>


---

## Solution

```java
class Solution {
     public static int characterReplacement(String s, int k) {
        int n = s.length();
        ArrayList<ArrayList<Integer>> pos = new ArrayList<>();
        for (int i = 0; i <= 30; i++) pos.add(new ArrayList<>());
        for (int i = 0; i < n; i++) pos.get(s.charAt(i) - 'A').add(i);
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, s, k, pos)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }
    private static boolean ok(int mid, String s, int k, ArrayList<ArrayList<Integer>> pos) {
        int n = s.length();
        int maxi = 0;
        for (int ch = 'A'; ch <= 'Z'; ch++) {
            if (pos.get(ch - 'A').size() == 0) continue;
            if (pos.get(ch - 'A').size() == 1) maxi = Math.max(maxi, k + 1);
            int current_k = k;
            ArrayList<Integer> temp = pos.get(ch - 'A');
            maxi = Math.max(maxi, solve(temp, k , s)); 
        }
        return maxi >= mid;
    }
    private static int solve(ArrayList<Integer> res, int k, String s) {
        int n = res.size();
        int left = 0 , right = 0;
        int current_maxi = 0, maxi = 0;
        while (left < n) {
            while (right + 1 < n && res.get(right + 1) - res.get(right) - 1 <= k) {
                k -= res.get(right + 1) - res.get(right) - 1;
                right++;
            }
            maxi = Math.max(maxi, Math.min(s.length() , res.get(right) - res.get(left) + 1 + k));
            left++;
            if (left - 1 >= 0 && left < n) k += res.get(left) - res.get(left - 1) - 1;
        }
        return maxi;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

