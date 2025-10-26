# 1923. Sentence Similarity Iii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/sentence-similarity-iii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1923-sentence-similarity-iii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/sentence-similarity-iii">1923. Sentence Similarity III</a></h2><h3>Medium</h3><hr><p>You are given two strings <code>sentence1</code> and <code>sentence2</code>, each representing a <strong>sentence</strong> composed of words. A sentence is a list of <strong>words</strong> that are separated by a <strong>single</strong> space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.</p>

<p>Two sentences <code>s1</code> and <code>s2</code> are considered <strong>similar</strong> if it is possible to insert an arbitrary sentence (<em>possibly empty</em>) inside one of these sentences such that the two sentences become equal. <strong>Note</strong> that the inserted sentence must be separated from existing words by spaces.</p>

<p>For example,</p>

<ul>
	<li><code>s1 = &quot;Hello Jane&quot;</code> and <code>s2 = &quot;Hello my name is Jane&quot;</code> can be made equal by inserting <code>&quot;my name is&quot;</code> between <code>&quot;Hello&quot;</code><font face="monospace"> </font>and <code>&quot;Jane&quot;</code><font face="monospace"> in s1.</font></li>
	<li><font face="monospace"><code>s1 = &quot;Frog cool&quot;</code> </font>and<font face="monospace"> <code>s2 = &quot;Frogs are cool&quot;</code> </font>are <strong>not</strong> similar, since although there is a sentence <code>&quot;s are&quot;</code> inserted into <code>s1</code>, it is not separated from <code>&quot;Frog&quot;</code> by a space.</li>
</ul>

<p>Given two sentences <code>sentence1</code> and <code>sentence2</code>, return <strong>true</strong> if <code>sentence1</code> and <code>sentence2</code> are <strong>similar</strong>. Otherwise, return <strong>false</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">sentence1 = &quot;My name is Haley&quot;, sentence2 = &quot;My Haley&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><code>sentence2</code> can be turned to <code>sentence1</code> by inserting &quot;name is&quot; between &quot;My&quot; and &quot;Haley&quot;.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">sentence1 = &quot;of&quot;, sentence2 = &quot;A lot of words&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>No single sentence can be inserted inside one of the sentences to make it equal to the other.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">sentence1 = &quot;Eating right now&quot;, sentence2 = &quot;Eating&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><code>sentence2</code> can be turned to <code>sentence1</code> by inserting &quot;right now&quot; at the end of the sentence.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li>
	<li><code>sentence1</code> and <code>sentence2</code> consist of lowercase and uppercase English letters and spaces.</li>
	<li>The words in <code>sentence1</code> and <code>sentence2</code> are separated by a single space.</li>
</ul>


---

## Solution

```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < s1.length && j < s2.length){
            if (!s1[i].equals(s2[j])) break;
            i++; j++;
        }
        if (i == s1.length || j == s2.length) return true;
        int len1 = s1.length - 1, len2 = s2.length - 1;
        while(len1 >= i && len2 >= j){
            if(!s1[len1].equals(s2[len2])) return false;
            len1--; len2--;
        }
        if (len1 < i || len2 < j) return true;
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

