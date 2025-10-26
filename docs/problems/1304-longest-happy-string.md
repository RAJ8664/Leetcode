# 1304. Longest Happy String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-happy-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1304-longest-happy-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-happy-string">1304. Longest Happy String</a></h2><h3>Medium</h3><hr><p>A string <code>s</code> is called <strong>happy</strong> if it satisfies the following conditions:</p>

<ul>
	<li><code>s</code> only contains the letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li><code>s</code> does not contain any of <code>&quot;aaa&quot;</code>, <code>&quot;bbb&quot;</code>, or <code>&quot;ccc&quot;</code> as a substring.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>a</code> occurrences of the letter <code>&#39;a&#39;</code>.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>b</code> occurrences of the letter <code>&#39;b&#39;</code>.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>c</code> occurrences of the letter <code>&#39;c&#39;</code>.</li>
</ul>

<p>Given three integers <code>a</code>, <code>b</code>, and <code>c</code>, return <em>the <strong>longest possible happy </strong>string</em>. If there are multiple longest happy strings, return <em>any of them</em>. If there is no such string, return <em>the empty string </em><code>&quot;&quot;</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 1, c = 7
<strong>Output:</strong> &quot;ccaccbcc&quot;
<strong>Explanation:</strong> &quot;ccbccacc&quot; would also be a correct answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 7, b = 1, c = 0
<strong>Output:</strong> &quot;aabaa&quot;
<strong>Explanation:</strong> It is the only correct answer in this case.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b, c &lt;= 100</code></li>
	<li><code>a + b + c &gt; 0</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        char ch;
        int freq;
        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
    static class sorting implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.freq, first.freq);
        }
    }
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(new sorting());
        if(a > 0) pq.offer(new Pair('a' , a));
        if(b > 0) pq.offer(new Pair('b' , b));
        if(c > 0) pq.offer(new Pair('c' , c));
        String ans = "";
        int a_used = 0 , b_used = 0, c_used = 0;
        while(pq.size() > 0) {
            Pair current = pq.poll();
            if(current.ch == 'a') {
                if(a_used < 2) {
                    ans += "a";
                    current.freq--;
                    a_used++;b_used = 0; c_used = 0;
                    if(current.freq > 0) pq.offer(current);
                }
                else {
                    if(pq.size() == 0) break;
                    Pair current1 = pq.poll();
                    ans += current1.ch;
                    current1.freq--;
                    if(current1.ch == 'a') {
                        a_used++; b_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'b') {
                        b_used++; a_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'c') {
                        c_used++; a_used = 0; b_used = 0;
                    }
                    if(current.freq > 0 ) pq.offer(current);
                    if(current1.freq > 0) pq.offer(current1);
                }

            }
            else if(current.ch == 'b') {
                 if(b_used < 2) {
                    ans += "b";
                    current.freq--;
                    b_used++; a_used = 0; c_used = 0;
                    if(current.freq > 0) pq.offer(current);
                }
                else {
                    if(pq.size() == 0) break;
                    Pair current1 = pq.poll();
                    ans += current1.ch;
                    current1.freq--;
                    if(current1.ch == 'a') {
                        a_used++; b_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'b') {
                        b_used++; a_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'c') {
                        c_used++; a_used = 0; b_used = 0;
                    }
                    if(current.freq > 0 ) pq.offer(current);
                    if(current1.freq > 0) pq.offer(current1);
                }
            }
            else if(current.ch == 'c') {
                 if(c_used < 2) {
                    ans += "c";
                    current.freq--;
                    c_used++; b_used = 0; a_used = 0;
                    if(current.freq > 0) pq.offer(current);
                }
                else {
                    if(pq.size() == 0) break;
                    Pair current1 = pq.poll();
                    ans += current1.ch;
                    current1.freq--;
                    if(current1.ch == 'a') {
                        a_used++; b_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'b') {
                        b_used++; a_used = 0; c_used = 0;
                    }
                    else if(current1.ch == 'c') {
                        c_used++; a_used = 0; b_used = 0;
                    }
                    if(current.freq > 0 ) pq.offer(current);
                    if(current1.freq > 0) pq.offer(current1);
                }
            }
        }
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

