# 3812. Smallest Palindromic Rearrangement I


---

<h2><a href="https://leetcode.com/problems/smallest-palindromic-rearrangement-i">3812. Smallest Palindromic Rearrangement I</a></h2><h3>Medium</h3><hr><p>You are given a <strong><span data-keyword="palindrome-string">palindromic</span></strong> string <code>s</code>.</p>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> palindromic <span data-keyword="permutation-string">permutation</span> of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;z&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;z&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>A string of only one character is already the lexicographically smallest palindrome.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;babab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abbba&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Rearranging <code>&quot;babab&quot;</code> &rarr; <code>&quot;abbba&quot;</code> gives the smallest lexicographic palindrome.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;daccad&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;acddca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Rearranging <code>&quot;daccad&quot;</code> &rarr; <code>&quot;acddca&quot;</code> gives the smallest lexicographic palindrome.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> is guaranteed to be palindromic.</li>
</ul>


## Solution

```java
class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        if (n == 1) return s;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;
        StringBuilder res = new StringBuilder();
        boolean flag = false;
        char toPlace = 'x';
        for (int i = 0; i < 25; i++) {
            if (freq[i] % 2 == 1) {
                char current = (char)('a' + i);
                flag = true;
                toPlace = current;
            }
        }
        for (int i = 0; i < 26; i++) {
            int times = freq[i] / 2;
            char current = (char)('a' + i);
            for (int j = 0; j < times; j++) res.append(current);
            freq[i] -= times;
        }
        if (flag == true) {
            freq[toPlace - 'a']--;
            res.append(toPlace);
        }
        for (int i = 25; i >= 0; i--) {
            int times = freq[i];
            char current = (char)('a' + i);
            for (int j = 0; j < times; j++) res.append(current);
            freq[i] -= times;
        }
        return res.toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

