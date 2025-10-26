# 1423. Maximum Number Of Occurrences Of A Substring

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1423-maximum-number-of-occurrences-of-a-substring){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring">1423. Maximum Number of Occurrences of a Substring</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code>, return the maximum number of occurrences of <strong>any</strong> substring under the following rules:</p>

<ul>
	<li>The number of unique characters in the substring must be less than or equal to <code>maxLetters</code>.</li>
	<li>The substring size must be between <code>minSize</code> and <code>maxSize</code> inclusive.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababcaab&quot;, maxLetters = 2, minSize = 3, maxSize = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;aab&quot; has 2 occurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaa&quot;, maxLetters = 1, minSize = 3, maxSize = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;aaa&quot; occur 2 times in the string. It can overlap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maxLetters &lt;= 26</code></li>
	<li><code>1 &lt;= minSize &lt;= maxSize &lt;= min(26, s.length)</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<String, Integer> map;
    private int maxi;
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        map = new HashMap<>(); maxi = 0;
        for (int i = minSize; i <= maxSize; i++) getSubstringsOfLengthK(s, i, maxLetters);
        return maxi;
    }
    private ArrayList<String> getSubstringsOfLengthK(String s, int k, int maxLetters) {
        int n = s.length();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i + k <= n) {
                String current = s.substring(i, i + k);
                HashSet<Character> set = new HashSet<>();
                for (int j = 0; j < current.length(); j++) set.add(current.charAt(j));
                if (set.size() <= maxLetters) map.put(current, map.getOrDefault(current, 0) + 1);
                maxi = Math.max(maxi, map.getOrDefault(current, 0));
            }
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

