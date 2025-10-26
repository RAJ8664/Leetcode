# 2095. Minimum Number Of Swaps To Make The String Balanced

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2095-minimum-number-of-swaps-to-make-the-string-balanced){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced">2095. Minimum Number of Swaps to Make the String Balanced</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> string <code>s</code> of <strong>even</strong> length <code>n</code>. The string consists of <strong>exactly</strong> <code>n / 2</code> opening brackets <code>&#39;[&#39;</code> and <code>n / 2</code> closing brackets <code>&#39;]&#39;</code>.</p>

<p>A string is called <strong>balanced</strong> if and only if:</p>

<ul>
	<li>It is the empty string, or</li>
	<li>It can be written as <code>AB</code>, where both <code>A</code> and <code>B</code> are <strong>balanced</strong> strings, or</li>
	<li>It can be written as <code>[C]</code>, where <code>C</code> is a <strong>balanced</strong> string.</li>
</ul>

<p>You may swap the brackets at <strong>any</strong> two indices <strong>any</strong> number of times.</p>

<p>Return <em>the <strong>minimum</strong> number of swaps to make </em><code>s</code> <em><strong>balanced</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;][][&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can make the string balanced by swapping index 0 with index 3.
The resulting string is &quot;[[]]&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;]]][[[&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can do the following to make the string balanced:
- Swap index 0 with index 4. s = &quot;[]][][&quot;.
- Swap index 1 with index 5. s = &quot;[[][]]&quot;.
The resulting string is &quot;[[][]]&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;[]&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already balanced.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>s[i]</code> is either <code>&#39;[&#39; </code>or <code>&#39;]&#39;</code>.</li>
	<li>The number of opening brackets <code>&#39;[&#39;</code> equals <code>n / 2</code>, and the number of closing brackets <code>&#39;]&#39;</code> equals <code>n / 2</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minSwaps(String s) {
        int n = s.length(), count = 0;
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == '[') st.add(current);
            else {
                if (st.size() == 0) count++;
                else st.pop();
            }
        }
        int ans = count / 2;
        if (count % 2 == 1) ans++;
        return ans;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

