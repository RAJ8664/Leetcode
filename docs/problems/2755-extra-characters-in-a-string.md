# 2755. Extra Characters In A String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/extra-characters-in-a-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2755-extra-characters-in-a-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/extra-characters-in-a-string">2755. Extra Characters in a String</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> string <code>s</code> and a dictionary of words <code>dictionary</code>. You have to break <code>s</code> into one or more <strong>non-overlapping</strong> substrings such that each substring is present in <code>dictionary</code>. There may be some <strong>extra characters</strong> in <code>s</code> which are not present in any of the substrings.</p>

<p>Return <em>the <strong>minimum</strong> number of extra characters left over if you break up </em><code>s</code><em> optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetscode&quot;, dictionary = [&quot;leet&quot;,&quot;code&quot;,&quot;leetcode&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can break s in two substrings: &quot;leet&quot; from index 0 to 3 and &quot;code&quot; from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;sayhelloworld&quot;, dictionary = [&quot;hello&quot;,&quot;world&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can break s in two substrings: &quot;hello&quot; from index 3 to 7 and &quot;world&quot; from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 50</code></li>
	<li><code>dictionary[i]</code>&nbsp;and <code>s</code> consists of only lowercase English letters</li>
	<li><code>dictionary</code> contains distinct words</li>
</ul>


---

## Solution

```java
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        int sz = s.length();
        for(var word : dictionary) set.add(word);
        int count[] = new int[sz + 1];
        for(int right = 1; right <= sz; right++){
                count[right] = 1 + count[right-1];
                for(int left = right; left > 0; left--){
                    String word = s.substring(left - 1, right);
                    if(set.contains(word)){
                        count[right] = Math.min(count[right], count[left - 1]);
                    }
                }
        }
        return count[sz];
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

