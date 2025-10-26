# 952. Word Subsets

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/word-subsets/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0952-word-subsets){ .md-button }

---

<h2><a href="https://leetcode.com/problems/word-subsets">952. Word Subsets</a></h2><h3>Medium</h3><hr><p>You are given two string arrays <code>words1</code> and <code>words2</code>.</p>

<p>A string <code>b</code> is a <strong>subset</strong> of string <code>a</code> if every letter in <code>b</code> occurs in <code>a</code> including multiplicity.</p>

<ul>
	<li>For example, <code>&quot;wrr&quot;</code> is a subset of <code>&quot;warrior&quot;</code> but is not a subset of <code>&quot;world&quot;</code>.</li>
</ul>

<p>A string <code>a</code> from <code>words1</code> is <strong>universal</strong> if for every string <code>b</code> in <code>words2</code>, <code>b</code> is a subset of <code>a</code>.</p>

<p>Return an array of all the <strong>universal</strong> strings in <code>words1</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words1 = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], words2 = [&quot;e&quot;,&quot;o&quot;]
<strong>Output:</strong> [&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words1 = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], words2 = [&quot;l&quot;,&quot;e&quot;]
<strong>Output:</strong> [&quot;apple&quot;,&quot;google&quot;,&quot;leetcode&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words1.length, words2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words1[i].length, words2[i].length &lt;= 10</code></li>
	<li><code>words1[i]</code> and <code>words2[i]</code> consist only of lowercase English letters.</li>
	<li>All the strings of <code>words1</code> are <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int n = words1.length, m = words2.length;
        int freq[] = new int[26];
        for (int i = 0; i < m; i++) {
            int temp_freq[] = new int[26];
            for (int j = 0; j < words2[i].length(); j++) temp_freq[words2[i].charAt(j) - 'a']++;
            for (int j = 0; j < 26; j++) freq[j] = Math.max(freq[j], temp_freq[j]);
        }
        int vis[] = new int[n];
        for (int i = 0; i < n; i++) {
            int temp_freq[] = new int[26];
            for (int j = 0; j < words1[i].length(); j++) temp_freq[words1[i].charAt(j) - 'a']++;
            for (int j = 0; j < 26; j++) if (temp_freq[j] < freq[j]) vis[i] = 1;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) if (vis[i] == 0) res.add(words1[i]);
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

