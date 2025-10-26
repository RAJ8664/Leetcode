# 474. Ones And Zeroes

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/ones-and-zeroes/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0474-ones-and-zeroes){ .md-button }

---

<h2><a href="https://leetcode.com/problems/ones-and-zeroes">474. Ones and Zeroes</a></h2><h3>Medium</h3><hr><p>You are given an array of binary strings <code>strs</code> and two integers <code>m</code> and <code>n</code>.</p>

<p>Return <em>the size of the largest subset of <code>strs</code> such that there are <strong>at most</strong> </em><code>m</code><em> </em><code>0</code><em>&#39;s and </em><code>n</code><em> </em><code>1</code><em>&#39;s in the subset</em>.</p>

<p>A set <code>x</code> is a <strong>subset</strong> of a set <code>y</code> if all elements of <code>x</code> are also elements of <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0001&quot;,&quot;111001&quot;,&quot;1&quot;,&quot;0&quot;], m = 5, n = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The largest subset with at most 5 0&#39;s and 3 1&#39;s is {&quot;10&quot;, &quot;0001&quot;, &quot;1&quot;, &quot;0&quot;}, so the answer is 4.
Other valid but smaller subsets include {&quot;0001&quot;, &quot;1&quot;} and {&quot;10&quot;, &quot;1&quot;, &quot;0&quot;}.
{&quot;111001&quot;} is an invalid subset because it contains 4 1&#39;s, greater than the maximum of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0&quot;,&quot;1&quot;], m = 1, n = 1
<strong>Output:</strong> 2
<b>Explanation:</b> The largest subset is {&quot;0&quot;, &quot;1&quot;}, so the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 600</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists only of digits <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int findMaxForm(String[] strs, int zero, int one) {
        int n = strs.length;
        int dp[][][] = new int[n + 1][zero + 1][one + 1];
        for (int current[][] : dp) for (int current1[] : current) Arrays.fill(current1, -1);
        int res = solve(0, strs, 0, 0, dp, zero, one);
        return res;
    }
    static int solve(int ind, String arr[] , int zero, int one, int dp[][][], int max_zero, int max_one) {
        if (ind >= arr.length) return 0;
        if (dp[ind][zero][one] != -1) return dp[ind][zero][one];
        int count0 = 0, count1 = 0, op1 = 0, op2 = 0;
        String current = arr[ind];
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) == '0') count0++;
            else count1++;
        }
        if (zero + count0 <= max_zero && one + count1 <= max_one) op1 = 1 + solve(ind + 1, arr, zero + count0 , one + count1 , dp, max_zero, max_one);
        op2 = solve(ind + 1, arr, zero, one, dp, max_zero, max_one);
        return dp[ind][zero][one] = Math.max(op1, op2);
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

