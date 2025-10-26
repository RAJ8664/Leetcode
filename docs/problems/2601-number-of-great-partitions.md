# 2601. Number Of Great Partitions

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-great-partitions/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2601-number-of-great-partitions){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-great-partitions">2601. Number of Great Partitions</a></h2><h3>Hard</h3><hr><p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers and an integer <code>k</code>.</p>

<p><strong>Partition</strong> the array into two ordered <strong>groups</strong> such that each element is in exactly <strong>one</strong> group. A partition is called great if the <strong>sum</strong> of elements of each group is greater than or equal to <code>k</code>.</p>

<p>Return <em>the number of <strong>distinct</strong> great partitions</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two partitions are considered distinct if some element <code>nums[i]</code> is in different groups in the two partitions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3], k = 4
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no great partitions for this array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,6], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can either put nums[0] in the first partition or in the second partition.
The great partitions will be ([6], [6]) and ([6], [6]).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int mod = (int)(1e9 + 7);
    private long dp[][];
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        long totalWays = 1;
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalWays = (totalWays * 2) % mod;
            totalSum += nums[i];
        }

        if (totalSum < 2 * k)
            return 0;
        dp = new long[n + 1][k + 1];
        for (long current[] : dp)
            Arrays.fill(current, -1);

        long count = solve(0, 0, nums, k);
        long res = (totalWays - 2 * count % mod + mod) % mod;

        return (int)(res);
    }

    private long solve(int ind, int sum, int arr[], int k) {
        if (ind >= arr.length) {
            if (sum < k)
                return 1;
            return 0;
        }

        if (dp[ind][sum] != -1)
            return dp[ind][sum];

        long op1 = solve(ind + 1, sum, arr, k);
        long op2 = 0;
        if (sum + arr[ind] <= k)
            op2 = solve(ind + 1, sum + arr[ind], arr, k);

        return dp[ind][sum] = (op1 + op2) % mod;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

