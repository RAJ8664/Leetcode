# 3872. Find Most Frequent Vowel And Consonant

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3872-find-most-frequent-vowel-and-consonant){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-most-frequent-vowel-and-consonant">3872. Find Most Frequent Vowel and Consonant</a></h2><h3>Easy</h3><hr><p>You are given a string <code>s</code> consisting of lowercase English letters (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>). </p>

<p>Your task is to:</p>

<ul>
	<li>Find the vowel (one of <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, or <code>&#39;u&#39;</code>) with the <strong>maximum</strong> frequency.</li>
	<li>Find the consonant (all other letters excluding vowels) with the <strong>maximum</strong> frequency.</li>
</ul>

<p>Return the sum of the two frequencies.</p>

<p><strong>Note</strong>: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If there are no vowels or no consonants in the string, consider their frequency as 0.</p>
The <strong>frequency</strong> of a letter <code>x</code> is the number of times it occurs in the string.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;successes&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The vowels are: <code>&#39;u&#39;</code> (frequency 1), <code>&#39;e&#39;</code> (frequency 2). The maximum frequency is 2.</li>
	<li>The consonants are: <code>&#39;s&#39;</code> (frequency 4), <code>&#39;c&#39;</code> (frequency 2). The maximum frequency is 4.</li>
	<li>The output is <code>2 + 4 = 6</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aeiaeia&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The vowels are: <code>&#39;a&#39;</code> (frequency 3), <code>&#39;e&#39;</code> ( frequency 2), <code>&#39;i&#39;</code> (frequency 2). The maximum frequency is 3.</li>
	<li>There are no consonants in <code>s</code>. Hence, maximum consonant frequency = 0.</li>
	<li>The output is <code>3 + 0 = 3</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>


---

## Solution

```java
class Solution {
    public int maxFreqSum(String s) {
        int n = s.length();
        int freq[] = new int[26];
        int maxiV = 0, maxiC = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            freq[s.charAt(i) - 'a']++;
            if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u')
                maxiV = Math.max(maxiV, freq[current - 'a']);
            else 
                maxiC = Math.max(maxiC, freq[current - 'a']);            
        } 
        return maxiV + maxiC;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

