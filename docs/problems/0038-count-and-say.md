# 38. Count And Say

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-and-say/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0038-count-and-say){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-and-say">38. Count and Say</a></h2><h3>Medium</h3><hr><p>The <strong>count-and-say</strong> sequence is a sequence of digit strings defined by the recursive formula:</p>

<ul>
	<li><code>countAndSay(1) = &quot;1&quot;</code></li>
	<li><code>countAndSay(n)</code> is the run-length encoding of <code>countAndSay(n - 1)</code>.</li>
</ul>

<p><a href="http://en.wikipedia.org/wiki/Run-length_encoding" target="_blank">Run-length encoding</a> (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string <code>&quot;3322251&quot;</code> we replace <code>&quot;33&quot;</code> with <code>&quot;23&quot;</code>, replace <code>&quot;222&quot;</code> with <code>&quot;32&quot;</code>, replace <code>&quot;5&quot;</code> with <code>&quot;15&quot;</code> and replace <code>&quot;1&quot;</code> with <code>&quot;11&quot;</code>. Thus the compressed string becomes <code>&quot;23321511&quot;</code>.</p>

<p>Given a positive integer <code>n</code>, return <em>the </em><code>n<sup>th</sup></code><em> element of the <strong>count-and-say</strong> sequence</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1211&quot;</span></p>

<p><strong>Explanation:</strong></p>

<pre>
countAndSay(1) = &quot;1&quot;
countAndSay(2) = RLE of &quot;1&quot; = &quot;11&quot;
countAndSay(3) = RLE of &quot;11&quot; = &quot;21&quot;
countAndSay(4) = RLE of &quot;21&quot; = &quot;1211&quot;
</pre>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>This is the base case.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you solve it iteratively?

---

## Solution

```java
class Solution {
    public String countAndSay(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == 1) res.append("1");
            else {
                String current = res.toString();
                res = new StringBuilder();
                res.append(findRLE(current));
            }
        }
        return res.toString();
    }
    private String findRLE(String s) {
        int n = s.length();
        StringBuilder res = new StringBuilder();
        char prev = s.charAt(0);
        int count = 1;
        for (int i = 1; i < n; i++) {
            char current = s.charAt(i);
            if (current == prev) count++;
            else {
                res.append(count);
                res.append(prev);
                count = 1;
                prev = current;
            }
        }
        if (count > 0) {
            res.append(count);
            res.append(prev);
        }
        return res.toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

