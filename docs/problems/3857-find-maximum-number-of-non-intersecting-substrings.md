# 3857. Find Maximum Number Of Non Intersecting Substrings


---

<h2><a href="https://leetcode.com/problems/find-maximum-number-of-non-intersecting-substrings">3857. Find Maximum Number of Non Intersecting Substrings</a></h2><h3>Medium</h3><hr><p>You are given a string <code>word</code>.</p>

<p>Return the <strong>maximum</strong> number of non-intersecting <strong>substrings</strong> of word that are at <strong>least</strong> four characters long and start and end with the same letter.</p>

<p>A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abcdeafdef&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The two substrings are <code>&quot;abcdea&quot;</code> and <code>&quot;fdef&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;bcdaaaab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only substring is <code>&quot;aaaa&quot;</code>. Note that we cannot <strong>also</strong> choose <code>&quot;bcdaaaab&quot;</code> since it intersects with the other substring.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>


## Solution

```java
class Solution {
    public int maxSubstrings(String s) {
        int n = s.length();
        int occurred[] = new int[26];
        Arrays.fill(occurred, -1);
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            boolean isSeen = occurred[ch - 'a'] != -1;
            if (isSeen) {
                if (occurred[ch - 'a'] - i + 1 >= 4) {
                    res++;
                    Arrays.fill(occurred, -1);
                }
            }
            else occurred[ch - 'a'] = i;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

