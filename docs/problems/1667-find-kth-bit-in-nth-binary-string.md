# 1667. Find Kth Bit In Nth Binary String

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1667-find-kth-bit-in-nth-binary-string){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-kth-bit-in-nth-binary-string">1667. Find Kth Bit in Nth Binary String</a></h2><h3>Medium</h3><hr><p>Given two positive integers <code>n</code> and <code>k</code>, the binary string <code>S<sub>n</sub></code> is formed as follows:</p>

<ul>
	<li><code>S<sub>1</sub> = &quot;0&quot;</code></li>
	<li><code>S<sub>i</sub> = S<sub>i - 1</sub> + &quot;1&quot; + reverse(invert(S<sub>i - 1</sub>))</code> for <code>i &gt; 1</code></li>
</ul>

<p>Where <code>+</code> denotes the concatenation operation, <code>reverse(x)</code> returns the reversed string <code>x</code>, and <code>invert(x)</code> inverts all the bits in <code>x</code> (<code>0</code> changes to <code>1</code> and <code>1</code> changes to <code>0</code>).</p>

<p>For example, the first four strings in the above sequence are:</p>

<ul>
	<li><code>S<sub>1 </sub>= &quot;0&quot;</code></li>
	<li><code>S<sub>2 </sub>= &quot;0<strong>1</strong>1&quot;</code></li>
	<li><code>S<sub>3 </sub>= &quot;011<strong>1</strong>001&quot;</code></li>
	<li><code>S<sub>4</sub> = &quot;0111001<strong>1</strong>0110001&quot;</code></li>
</ul>

<p>Return <em>the</em> <code>k<sup>th</sup></code> <em>bit</em> <em>in</em> <code>S<sub>n</sub></code>. It is guaranteed that <code>k</code> is valid for the given <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> S<sub>3</sub> is &quot;<strong><u>0</u></strong>111001&quot;.
The 1<sup>st</sup> bit is &quot;0&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 11
<strong>Output:</strong> &quot;1&quot;
<strong>Explanation:</strong> S<sub>4</sub> is &quot;0111001101<strong><u>1</u></strong>0001&quot;.
The 11<sup>th</sup> bit is &quot;1&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n</sup> - 1</code></li>
</ul>


---

## Solution

```java
class Solution {
    public char findKthBit(int n, int k) {
        String dp[] = new String[n + 1];
        dp[1] = "0";
        if (n == k) if (k == 1) return '0';
        dp[2] = "011";
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + "1" + reverse(invert(dp[i - 1]));
        }
        String ans = dp[n];
        return ans.charAt(k - 1);
    }

   private String invert(String s){
        int n = s.length();
        StringBuilder inv = new StringBuilder();
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '1'){
                inv.append('0');
            }else{
                inv.append('1');
            }
        }
        return inv.toString();
    }

    private String reverse(String s){
        StringBuilder rev = new StringBuilder(s);
        return rev.reverse().toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

