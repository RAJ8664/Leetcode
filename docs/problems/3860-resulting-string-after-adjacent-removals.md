# 3860. Resulting String After Adjacent Removals


---

<h2><a href="https://leetcode.com/problems/resulting-string-after-adjacent-removals">3860. Resulting String After Adjacent Removals</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>You <strong>must</strong> repeatedly perform the following operation while the string <code>s</code> has <strong>at least</strong> two <strong>consecutive </strong>characters:</p>

<ul>
	<li>Remove the <strong>leftmost</strong> pair of <strong>adjacent</strong> characters in the string that are <strong>consecutive</strong> in the alphabet, in either order (e.g., <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>, or <code>&#39;b&#39;</code> and <code>&#39;a&#39;</code>).</li>
	<li>Shift the remaining characters to the left to fill the gap.</li>
</ul>

<p>Return the resulting string after no more operations can be performed.</p>

<p><strong>Note:</strong> Consider the alphabet as circular, thus <code>&#39;a&#39;</code> and <code>&#39;z&#39;</code> are consecutive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;c&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;ab&quot;</code> from the string, leaving <code>&quot;c&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;c&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;adcb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;dc&quot;</code> from the string, leaving <code>&quot;ab&quot;</code> as the remaining string.</li>
	<li>Remove <code>&quot;ab&quot;</code> from the string, leaving <code>&quot;&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zadb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;db&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;za&quot;</code> from the string, leaving <code>&quot;db&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;db&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>


## Solution

```java
class Solution {
    public String resultingString(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (i == 0) st.add(current - 'a');
            else {
                if (current == 'a') {
                    System.out.println(i);
                    if (st.size() == 0) st.add(current - 'a');
                    else {
                        if (st.peek() == 'b' - 'a') st.pop();
                        else if (st.peek() == 'z' - 'a') st.pop();
                        else st.add(current - 'a');
                    }
                    continue;
                }
                if (current == 'z') {
                    if (st.size() == 0) st.add(current - 'a');
                    else {
                        if (st.peek() == 'a' - 'a') st.pop();
                        else if (st.peek() == 'y' - 'a') st.pop();
                        else st.add(current - 'a');
                    }
                    continue;
                }
                if (st.size() > 0 && (current - 'a' + 1) % 26 == st.peek()) {
                    st.pop();
                }
                else if (st.size() > 0 && (current - 'a' - 1) % 26 == st.peek()) {
                    st.pop();
                }
                else st.add(current - 'a');
            }
        }
        StringBuilder res = new StringBuilder();
        while (st.size() > 0) res.append((char)(st.pop() + 'a'));
        return res.reverse().toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

