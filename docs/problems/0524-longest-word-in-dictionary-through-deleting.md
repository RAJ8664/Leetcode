# 524. Longest Word In Dictionary Through Deleting

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0524-longest-word-in-dictionary-through-deleting){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-word-in-dictionary-through-deleting">524. Longest Word in Dictionary through Deleting</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code> and a string array <code>dictionary</code>, return <em>the longest string in the dictionary that can be formed by deleting some of the given string characters</em>. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abpcplea&quot;, dictionary = [&quot;ale&quot;,&quot;apple&quot;,&quot;monkey&quot;,&quot;plea&quot;]
<strong>Output:</strong> &quot;apple&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abpcplea&quot;, dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
<strong>Output:</strong> &quot;a&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 1000</code></li>
	<li><code>s</code> and <code>dictionary[i]</code> consist of lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length();
        int flag[] = new int[dictionary.size()];
        for (int i = 0; i < dictionary.size(); i++) {
            if (canForm(s, dictionary.get(i)))
                flag[i] = 1;
        }
        int maxi = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            if (flag[i] == 1)
                maxi = Math.max(maxi, dictionary.get(i).length());
        }
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < dictionary.size(); i++) {
            if (flag[i] == 1 && dictionary.get(i).length() == maxi)
                res.add(dictionary.get(i));
        }
        Collections.sort(res);
        if (res.size() == 0)
            return "";
        return res.get(0);
    }

    private boolean canForm(String s, String t) {
        int n = s.length(), m = t.length();
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

