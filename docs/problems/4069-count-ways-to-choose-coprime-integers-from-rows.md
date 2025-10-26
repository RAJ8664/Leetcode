# 4069. Count Ways To Choose Coprime Integers From Rows


---

<h2><a href="https://leetcode.com/problems/count-ways-to-choose-coprime-integers-from-rows">4069. Count Ways to Choose Coprime Integers from Rows</a></h2><h3>Hard</h3><hr><p>You are given a <code>m x n</code> matrix <code>mat</code> of positive integers.</p>

<p>Return an integer denoting the number of ways to choose <strong>exactly one</strong> integer from each row of <code>mat</code> such that the <strong>greatest common divisor</strong> of all chosen integers is 1.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th align="center" style="border: 1px solid black;">Chosen integer in the first row</th>
			<th align="center" style="border: 1px solid black;">Chosen integer in the second row</th>
			<th align="center" style="border: 1px solid black;">Greatest common divisor of chosen integers</th>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>

<p>3 of these combinations have a greatest common divisor of 1. Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[2,2],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Every combination has a greatest common divisor of 2. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == mat.length &lt;= 150</code></li>
	<li><code>1 &lt;= n == mat[i].length &lt;= 150</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 150</code></li>
</ul>


## Solution

```java
class Solution {
    private int mod = (int)(1e9 + 7);
    private int dp[][];
    public int countCoprime(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        dp = new int[n + 1][200];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(0, 0, mat);
    }
    private int solve(int currRow, int gcd, int arr[][]) {
        if (currRow >= arr.length) {
            if (gcd == 1)
                return 1;
            return 0;
        }
        if (dp[currRow][gcd] != -1)
            return dp[currRow][gcd];
        int ans = 0;
        for (int j = 0; j < arr[0].length; j++) {
            int op1 = solve(currRow + 1, GCD(gcd, arr[currRow][j]), arr);
            ans = (ans + op1) % mod;
        }
        return dp[currRow][gcd] = ans;
    }
    private int GCD(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0) 
            return a;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

