# 3714. Maximum And Minimum Sums Of At Most Size K Subsequences

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3714-maximum-and-minimum-sums-of-at-most-size-k-subsequences){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences">3714. Maximum and Minimum Sums of at Most Size K Subsequences</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code> and a positive integer <code>k</code>. Return the sum of the <strong>maximum</strong> and <strong>minimum</strong> elements of all <strong><span data-keyword="subsequence-sequence-nonempty">subsequences</span></strong> of <code>nums</code> with <strong>at most</strong> <code>k</code> elements.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> 24</p>

<p><strong>Explanation:</strong></p>

<p>The subsequences of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subsequence </b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 3]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">24</td>
		</tr>
	</tbody>
</table>

<p>The output would be 24.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,0,6], k = 1</span></p>

<p><strong>Output:</strong> 2<span class="example-io">2</span></p>

<p><strong>Explanation: </strong></p>

<p>For subsequences with exactly 1 element, the minimum and maximum values are the element itself. Therefore, the total is <code>5 + 5 + 0 + 0 + 6 + 6 = 22</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], k = 2</span></p>

<p><strong>Output:</strong> 12</p>

<p><strong>Explanation:</strong></p>

<p>The subsequences <code>[1, 1]</code> and <code>[1]</code> each appear 3 times. For all of them, the minimum and maximum are both 1. Thus, the total is 12.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code><font face="monospace">1 &lt;= k &lt;= min(70, nums.length)</font></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private long[] factorials = new long[(int)(1e5 + 10)];
    private long[] invFactorials = new long[(int)(1e5 + 10)];
    private int mod = (int)(1e9 + 7);
    public int minMaxSums(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        preCompFacts();

        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                ans = add(ans, mul((long)(nums[i] * 1L), nCk(n - i - 1, j - 1)));
                ans = add(ans, mul((long)(nums[i] * 1L), nCk(i, j - 1)));
            }
        }
        return (int)(ans);
    }

    private void preCompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    private long nCk(int n, int k) {
        if (n - k < 0)
            return 0;
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    private long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    private long add(long a, long b) {
        a += b;
        if (a >= mod)
            a -= mod;
        return a;
    }

    private long mul(long a, long b) {
        return (long)((long)((a % mod) * 1L * (b % mod)) % mod);
    }


}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

