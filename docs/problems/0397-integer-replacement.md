# 397. Integer Replacement

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/integer-replacement/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0397-integer-replacement){ .md-button }

---

<h2><a href="https://leetcode.com/problems/integer-replacement">397. Integer Replacement</a></h2><h3>Medium</h3><hr><p>Given a positive integer <code>n</code>,&nbsp;you can apply one of the following&nbsp;operations:</p>

<ol>
	<li>If <code>n</code> is even, replace <code>n</code> with <code>n / 2</code>.</li>
	<li>If <code>n</code> is odd, replace <code>n</code> with either <code>n + 1</code> or <code>n - 1</code>.</li>
</ol>

<p>Return <em>the minimum number of operations needed for</em> <code>n</code> <em>to become</em> <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> 3
<strong>Explanation:</strong> 8 -&gt; 4 -&gt; 2 -&gt; 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> 4
<strong>Explanation: </strong>7 -&gt; 8 -&gt; 4 -&gt; 2 -&gt; 1
or 7 -&gt; 6 -&gt; 3 -&gt; 2 -&gt; 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<Long, Long> memo;
    public int integerReplacement(int n) {
        memo = new HashMap<>();
        long res = solve((long)(n));
        return (int)(res);
    }
    private long solve(long n) {
        if (n == 1) return 0;
        if (memo.containsKey((long)(n))) return memo.get(n);
        if (n % 2 == 0) return 1 + solve(n / 2);
        long op1 = 1 + solve(n + 1);
        long op2 = 1 + solve(n - 1);
        memo.put(n , (long)(Math.min(op1, op2)));
        return (long)(Math.min(op1, op2));
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

