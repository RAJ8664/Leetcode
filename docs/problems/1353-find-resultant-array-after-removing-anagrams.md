# 1353. Find Resultant Array After Removing Anagrams

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1353-find-resultant-array-after-removing-anagrams){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-resultant-array-after-removing-anagrams">1353. Find Resultant Array After Removing Anagrams</a></h2><h3>Easy</h3><hr><p>You are given a <strong>0-indexed</strong> string array <code>words</code>, where <code>words[i]</code> consists of lowercase English letters.</p>

<p>In one operation, select any index <code>i</code> such that <code>0 &lt; i &lt; words.length</code> and <code>words[i - 1]</code> and <code>words[i]</code> are <strong>anagrams</strong>, and <strong>delete</strong> <code>words[i]</code> from <code>words</code>. Keep performing this operation as long as you can select an index that satisfies the conditions.</p>

<p>Return <code>words</code> <em>after performing all operations</em>. It can be shown that selecting the indices for each operation in <strong>any</strong> arbitrary order will lead to the same result.</p>

<p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once. For example, <code>&quot;dacb&quot;</code> is an anagram of <code>&quot;abdc&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abba&quot;,&quot;baba&quot;,&quot;bbaa&quot;,&quot;cd&quot;,&quot;cd&quot;]
<strong>Output:</strong> [&quot;abba&quot;,&quot;cd&quot;]
<strong>Explanation:</strong>
One of the ways we can obtain the resultant array is by using the following operations:
- Since words[2] = &quot;bbaa&quot; and words[1] = &quot;baba&quot; are anagrams, we choose index 2 and delete words[2].
  Now words = [&quot;abba&quot;,&quot;baba&quot;,&quot;cd&quot;,&quot;cd&quot;].
- Since words[1] = &quot;baba&quot; and words[0] = &quot;abba&quot; are anagrams, we choose index 1 and delete words[1].
  Now words = [&quot;abba&quot;,&quot;cd&quot;,&quot;cd&quot;].
- Since words[2] = &quot;cd&quot; and words[1] = &quot;cd&quot; are anagrams, we choose index 2 and delete words[2].
  Now words = [&quot;abba&quot;,&quot;cd&quot;].
We can no longer perform any operations, so [&quot;abba&quot;,&quot;cd&quot;] is the final answer.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;,&quot;e&quot;]
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;,&quot;e&quot;]
<strong>Explanation:</strong>
No two adjacent strings in words are anagrams of each other, so no operations are performed.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        Stack<String> st = new Stack<>();
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            if (st.size() == 0)
                st.add(current);
            else {
                if (isAnagram(st.peek(), current))
                    continue;
                else 
                    st.add(current);
            }
        }
        while (st.size() > 0)
            res.add(st.pop());
        Collections.reverse(res);
        return res;
    }

    private boolean isAnagram(String s, String t) {
        int n = s.length(), m = t.length();
        int freq1[] = new int[26];
        int freq2[] = new int[26];
        for (int i = 0; i < n; i++)
            freq1[s.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++) 
            freq2[t.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            if (freq1[i] != freq2[i]) 
                return false;
        return true;
    } 
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

