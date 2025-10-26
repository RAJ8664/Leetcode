# 2520. Using A Robot To Print The Lexicographically Smallest String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2520-using-a-robot-to-print-the-lexicographically-smallest-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string">2520. Using a Robot to Print the Lexicographically Smallest String</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> and a robot that currently holds an empty string <code>t</code>. Apply one of the following operations until <code>s</code> and <code>t</code> <strong>are both empty</strong>:</p>

<ul>
	<li>Remove the <strong>first</strong> character of a string <code>s</code> and give it to the robot. The robot will append this character to the string <code>t</code>.</li>
	<li>Remove the <strong>last</strong> character of a string <code>t</code> and give it to the robot. The robot will write this character on paper.</li>
</ul>

<p>Return <em>the lexicographically smallest string that can be written on the paper.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zza&quot;
<strong>Output:</strong> &quot;azz&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;zza&quot;, t=&quot;&quot;.
Perform first operation three times p=&quot;&quot;, s=&quot;&quot;, t=&quot;zza&quot;.
Perform second operation three times p=&quot;azz&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bac&quot;
<strong>Output:</strong> &quot;abc&quot;
<strong>Explanation:</strong> Let p denote the written string.
Perform first operation twice p=&quot;&quot;, s=&quot;c&quot;, t=&quot;ba&quot;. 
Perform second operation twice p=&quot;ab&quot;, s=&quot;c&quot;, t=&quot;&quot;. 
Perform first operation p=&quot;ab&quot;, s=&quot;&quot;, t=&quot;c&quot;. 
Perform second operation p=&quot;abc&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bdda&quot;
<strong>Output:</strong> &quot;addb&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;bdda&quot;, t=&quot;&quot;.
Perform first operation four times p=&quot;&quot;, s=&quot;&quot;, t=&quot;bdda&quot;.
Perform second operation four times p=&quot;addb&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int freq[] = new int[26];
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (check(ch - 1, freq)) {
                st.add(ch);
                freq[ch]--;
            }
            else {
                sb.append((char)(ch + 'a'));
                freq[ch]--;
                while (st.size() > 0 && !check(st.peek() - 1, freq)) {
                    sb.append((char)(st.pop() + 'a'));
                }
            }
        }
        while (st.size() > 0) sb.append((char)(st.pop() + 'a'));
        return sb.toString();
    }
    private boolean check(int ch, int freq[]) {
        for (int i = 0; i <= ch; i++) if (freq[i] > 0) return true;
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

