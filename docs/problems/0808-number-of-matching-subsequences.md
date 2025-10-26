# 808. Number Of Matching Subsequences

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-matching-subsequences/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0808-number-of-matching-subsequences){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-matching-subsequences">808. Number of Matching Subsequences</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code> and an array of strings <code>words</code>, return <em>the number of</em> <code>words[i]</code> <em>that is a subsequence of</em> <code>s</code>.</p>

<p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

<ul>
	<li>For example, <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;abcde&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;, words = [&quot;a&quot;,&quot;bb&quot;,&quot;acd&quot;,&quot;ace&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three strings in words that are a subsequence of s: &quot;a&quot;, &quot;acd&quot;, &quot;ace&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dsahjpjauf&quot;, words = [&quot;ahjpjau&quot;,&quot;ja&quot;,&quot;ahbwzgqnuk&quot;,&quot;tnmlanowax&quot;]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of only lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<Character, TreeSet<Integer>> map;
    public int numMatchingSubseq(String s, String[] words) {
        map = new HashMap<>();
        preProcess(s);
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (check(words[i])) 
                count++;
        }
        return count;
    }
    private boolean check(String s) {
        int n = s.length();
        int ind = -1;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (!map.containsKey(current)) 
                return false;
            TreeSet<Integer> currChar = map.get(current);
            Integer next = currChar.higher(ind);
            if (next == null) 
                return false;
            ind = next;
        }
        return true;
    }
    private void preProcess(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (!map.containsKey(current))
                map.put(current, new TreeSet<>());
            map.get(current).add(i);
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

