# 312. Burst Balloons

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/burst-balloons/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0312-burst-balloons){ .md-button }

---

<h2><a href="https://leetcode.com/problems/burst-balloons">312. Burst Balloons</a></h2><h3>Hard</h3><hr><p>You are given <code>n</code> balloons, indexed from <code>0</code> to <code>n - 1</code>. Each balloon is painted with a number on it represented by an array <code>nums</code>. You are asked to burst all the balloons.</p>

<p>If you burst the <code>i<sup>th</sup></code> balloon, you will get <code>nums[i - 1] * nums[i] * nums[i + 1]</code> coins. If <code>i - 1</code> or <code>i + 1</code> goes out of bounds of the array, then treat it as if there is a balloon with a <code>1</code> painted on it.</p>

<p>Return <em>the maximum coins you can collect by bursting the balloons wisely</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,8]
<strong>Output:</strong> 167
<strong>Explanation:</strong>
nums = [3,1,5,8] --&gt; [3,5,8] --&gt; [3,8] --&gt; [8] --&gt; []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5]
<strong>Output:</strong> 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int dp[][];
    public int maxCoins(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int arr[] = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++)
            arr[i] = nums[i - 1];
        int res = solve(1, n, arr);
        return res;    
    }
    private int solve(int i, int j, int arr[]) {
        if (i > j) return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int ans = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            ans = Math.max(ans, arr[i - 1] * arr[k] * arr[j + 1] + solve(i, k - 1, arr) + solve(k + 1, j, arr));
        }
        return dp[i][j] = ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

