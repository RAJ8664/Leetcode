# 3106. Length Of The Longest Subsequence That Sums To Target

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3106-length-of-the-longest-subsequence-that-sums-to-target){ .md-button }

---

<h2><a href="https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target">3106. Length of the Longest Subsequence That Sums to Target</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code>, and an integer <code>target</code>.</p>

<p>Return <em>the <strong>length of the longest subsequence</strong> of</em> <code>nums</code> <em>that sums up to</em> <code>target</code>. <em>If no such subsequence exists, return</em> <code>-1</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], target = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 subsequences with a sum equal to 9: [4,5], [1,3,5], and [2,3,4]. The longest subsequences are [1,3,5], and [2,3,4]. Hence, the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,3,2,1,5], target = 7
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 5 subsequences with a sum equal to 7: [4,3], [4,1,2], [4,2,1], [1,1,5], and [1,3,2,1]. The longest subsequence is [1,3,2,1]. Hence, the answer is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,5,4,5], target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that nums has no subsequence that sums up to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;
import java.util.List;

class Solution {
    private int dp[][];
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        dp = new int[n + 1][target + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = solve(0, target, nums);
        System.out.println(Integer.MIN_VALUE / 10);
        if (res <= 0)
            return -1;
        return res;
    }
    private int solve(int ind, int sumLeft, List<Integer> arr) {
        if (ind >= arr.size()) {
            if (sumLeft == 0)
                return 0;
            return Integer.MIN_VALUE / 10;
        }

        if (dp[ind][sumLeft] != -1)
            return dp[ind][sumLeft];

        int op1 = solve(ind + 1, sumLeft, arr);
        int op2 = Integer.MIN_VALUE / 10;
        if (sumLeft >= arr.get(ind))
            op2 = 1 + solve(ind + 1, sumLeft - arr.get(ind), arr);

        return dp[ind][sumLeft] = Math.max(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

