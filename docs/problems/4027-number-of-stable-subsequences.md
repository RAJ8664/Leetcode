# 4027. Number Of Stable Subsequences


---

<h2><a href="https://leetcode.com/problems/number-of-stable-subsequences">4027. Number of Stable Subsequences</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subsequence-array-nonempty">subsequence</span></strong> is <strong>stable</strong> if it does not contain <strong>three consecutive</strong> elements with the <strong>same</strong> parity when the subsequence is read <strong>in order</strong> (i.e., consecutive <strong>inside the subsequence</strong>).</p>

<p>Return the number of stable subsequences.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Stable subsequences are <code>[1]</code>, <code>[3]</code>, <code>[5]</code>, <code>[1, 3]</code>, <code>[1, 5]</code>, and <code>[3, 5]</code>.</li>
	<li>Subsequence <code>[1, 3, 5]</code> is not stable because it contains three consecutive odd numbers. Thus, the answer is 6.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = </span>[2,3,4,2]</p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The only subsequence that is not stable is <code>[2, 4, 2]</code>, which contains three consecutive even numbers.</li>
	<li>All other subsequences are stable. Thus, the answer is 14.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>​​​​​​​5</sup></code></li>
</ul>


## Solution

```java
class Solution {
    private int dp[][][];
    private int mod = (int)(1e9 + 7);
    public int countStableSubsequences(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][4][4];
        for (int current[][] : dp)
            for (int current1[] : current)
                Arrays.fill(current1, -1);
        int res = solve(0, -1, 0, nums);
        return res;
    }

    private int solve(int ind, int prevParity, int consParity, int arr[]) {
        if (ind >= arr.length) {
            return (prevParity != -1) ? 1 : 0;
        }
        if (dp[ind][prevParity + 2][consParity] != -1)
            return dp[ind][prevParity + 2][consParity];
        long ans = 0L;
        ans += solve(ind + 1, prevParity, consParity, arr);
        int curP = arr[ind] % 2;
        if (prevParity == -1) {
            ans += solve(ind + 1, curP, 1, arr);
        } else {
            if (curP == prevParity) {
                if (consParity + 1 < 3) {
                    ans += solve(ind + 1, curP, consParity + 1, arr);
                }
            } else {
                ans += solve(ind + 1, curP, 1, arr);
            }
        }
        ans %= mod;
        dp[ind][prevParity + 2][consParity] = (int) ans;
        return dp[ind][prevParity + 2][consParity];
    }
}

```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

