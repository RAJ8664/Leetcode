# 1159. Smallest Subsequence Of Distinct Characters

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1159-smallest-subsequence-of-distinct-characters){ .md-button }

---

<h2><a href="https://leetcode.com/problems/smallest-subsequence-of-distinct-characters">1159. Smallest Subsequence of Distinct Characters</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code>, return <em>the </em><span data-keyword="lexicographically-smaller-string"><em>lexicographically smallest</em></span> <span data-keyword="subsequence-string"><em>subsequence</em></span><em> of</em> <code>s</code> <em>that contains all the distinct characters of</em> <code>s</code> <em>exactly once</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcabc&quot;
<strong>Output:</strong> &quot;abc&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbacdcbc&quot;
<strong>Output:</strong> &quot;acdb&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<strong>Note:</strong> This question is the same as 316: <a href="https://leetcode.com/problems/remove-duplicate-letters/" target="_blank">https://leetcode.com/problems/remove-duplicate-letters/</a>

---

## Solution

```java
class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) lastIndex[s.charAt(i) - 'a'] = i; 
        boolean[] seen = new boolean[26]; 
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int currentChar = s.charAt(i) - 'a';
            if (seen[currentChar]) continue; 
            while (!stack.isEmpty() && stack.peek() > currentChar && i < lastIndex[stack.peek()]) seen[stack.pop()] = false;
            stack.push(currentChar); 
            seen[currentChar] = true;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) result.append((char) (stack.pop() + 'a'));
        return result.reverse().toString();
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

