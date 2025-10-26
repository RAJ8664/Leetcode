# 2882. Ways To Express An Integer As Sum Of Powers

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2882-ways-to-express-an-integer-as-sum-of-powers){ .md-button }

---

<h2><a href="https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers">2882. Ways to Express an Integer as Sum of Powers</a></h2><h3>Medium</h3><hr><p>Given two <strong>positive</strong> integers <code>n</code> and <code>x</code>.</p>

<p>Return <em>the number of ways </em><code>n</code><em> can be expressed as the sum of the </em><code>x<sup>th</sup></code><em> power of <strong>unique</strong> positive integers, in other words, the number of sets of unique integers </em><code>[n<sub>1</sub>, n<sub>2</sub>, ..., n<sub>k</sub>]</code><em> where </em><code>n = n<sub>1</sub><sup>x</sup> + n<sub>2</sub><sup>x</sup> + ... + n<sub>k</sub><sup>x</sup></code><em>.</em></p>

<p>Since the result can be very large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>For example, if <code>n = 160</code> and <code>x = 3</code>, one way to express <code>n</code> is <code>n = 2<sup>3</sup> + 3<sup>3</sup> + 5<sup>3</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10, x = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can express n as the following: n = 3<sup>2</sup> + 1<sup>2</sup> = 10.
It can be shown that it is the only way to express 10 as the sum of the 2<sup>nd</sup> power of unique integers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, x = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can express n in the following ways:
- n = 4<sup>1</sup> = 4.
- n = 3<sup>1</sup> + 1<sup>1</sup> = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= x &lt;= 5</code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private int dp[][];
    private int mod = 1000000007;
    public int numberOfWays(int n, int x) {
        ArrayList<Integer> arr = new ArrayList<>();
        int idx = 1;
        while (true) {
            int current = (int)(Math.pow(idx++, x));
            if (current > n)
                break;
            arr.add(current);
        }
        dp = new int[arr.size() + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = solve(0, n, arr);
        return res;
    }
    private int solve(int ind, int sumLeft, ArrayList<Integer> arr) {
        if (sumLeft == 0)
            return 1;
        if (ind >= arr.size()) {
            if (sumLeft == 0)
                return 1;
            return 0;
        }
        if (dp[ind][sumLeft] != -1)
            return dp[ind][sumLeft];

        int op1 = solve(ind + 1, sumLeft, arr);
        int op2 = 0;
        if (sumLeft >= arr.get(ind))
            op2 = solve(ind + 1, sumLeft - arr.get(ind), arr);

        return dp[ind][sumLeft] = (op1 + op2) % mod;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

