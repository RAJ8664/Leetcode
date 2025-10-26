# 3033. Apply Operations To Make Two Strings Equal

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/apply-operations-to-make-two-strings-equal/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3033-apply-operations-to-make-two-strings-equal){ .md-button }

---

<h2><a href="https://leetcode.com/problems/apply-operations-to-make-two-strings-equal">3033. Apply Operations to Make Two Strings Equal</a></h2><h3>Medium</h3><hr><p>You are given two <strong>0-indexed</strong> binary strings <code>s1</code> and <code>s2</code>, both of length <code>n</code>, and a positive integer <code>x</code>.</p>

<p>You can perform any of the following operations on the string <code>s1</code> <strong>any</strong> number of times:</p>

<ul>
	<li>Choose two indices <code>i</code> and <code>j</code>, and flip both <code>s1[i]</code> and <code>s1[j]</code>. The cost of this operation is <code>x</code>.</li>
	<li>Choose an index <code>i</code> such that <code>i &lt; n - 1</code> and flip both <code>s1[i]</code> and <code>s1[i + 1]</code>. The cost of this operation is <code>1</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> cost needed to make the strings </em><code>s1</code><em> and </em><code>s2</code><em> equal, or return </em><code>-1</code><em> if it is impossible.</em></p>

<p><strong>Note</strong> that flipping a character means changing it from <code>0</code> to <code>1</code> or vice-versa.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;1100011000&quot;, s2 = &quot;0101001010&quot;, x = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can do the following operations:
- Choose i = 3 and apply the second operation. The resulting string is s1 = &quot;110<u><strong>11</strong></u>11000&quot;.
- Choose i = 4 and apply the second operation. The resulting string is s1 = &quot;1101<strong><u>00</u></strong>1000&quot;.
- Choose i = 0 and j = 8 and apply the first operation. The resulting string is s1 = &quot;<u><strong>0</strong></u>1010010<u><strong>1</strong></u>0&quot; = s2.
The total cost is 1 + 1 + 2 = 4. It can be shown that it is the minimum cost possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;10110&quot;, s2 = &quot;00011&quot;, x = 4
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to make the two strings equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n, x &lt;= 500</code></li>
	<li><code>s1</code> and <code>s2</code> consist only of the characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (s1.charAt(i) != s2.charAt(i)) 
                arr.add(i);
        n = arr.size();
        if (n % 2 == 1) 
            return -1;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, (int)(1e9));
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = x + dp[i + 1];
            if (i < n - 1) 
                dp[i] = Math.min(dp[i], 2 * (arr.get(i + 1) - arr.get(i)) + dp[i + 2]);
        } 
        return dp[0] / 2;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

