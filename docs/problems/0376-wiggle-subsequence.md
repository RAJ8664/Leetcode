# 376. Wiggle Subsequence

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/wiggle-subsequence/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0376-wiggle-subsequence){ .md-button }

---

<h2><a href="https://leetcode.com/problems/wiggle-subsequence">376. Wiggle Subsequence</a></h2><h3>Medium</h3><hr><p>A <strong>wiggle sequence</strong> is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.</p>

<ul>
	<li>For example, <code>[1, 7, 4, 9, 2, 5]</code> is a <strong>wiggle sequence</strong> because the differences <code>(6, -3, 5, -7, 3)</code> alternate between positive and negative.</li>
	<li>In contrast, <code>[1, 4, 7, 2, 5]</code> and <code>[1, 7, 4, 5, 5]</code> are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.</li>
</ul>

<p>A <strong>subsequence</strong> is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.</p>

<p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>wiggle subsequence</strong> of </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,7,4,9,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,17,5,10,13,15,10,5,16,8]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are several subsequences that achieve this length.
One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7,8,9]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve this in <code>O(n)</code> time?</p>


---

## Solution

```java
class Solution {
    private int dp[][][];
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][n + 1][3];
        for (int current[][] : dp) for (int current1[] : current) Arrays.fill(current1, -1);
        int res = solve(0, -1, -1, nums);
        return res;
    }

    private int solve(int ind, int prev_diff, int prev_ind, int arr[]) {
        if (ind >= arr.length) return 0;
        if (dp[ind][prev_ind + 1][prev_diff + 1] != -1) return dp[ind][prev_ind + 1][prev_diff + 1];
        if (prev_ind == -1) {
            int op1 = 0, op2 = 0;
            op1 = 1 + solve(ind + 1, prev_diff, ind, arr);
            op2 = solve(ind + 1, prev_diff, prev_ind, arr);
            return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
        } 
        else if (prev_diff == -1) {
            int op1 = 0, op2 = 0;
            if (arr[ind] - arr[prev_ind] > 0) op1 = 1 + solve(ind + 1, 1, ind, arr);
            if (arr[ind] - arr[prev_ind] < 0) op1 = 1 + solve(ind + 1, 0, ind, arr); 
            op2 = solve(ind + 1, prev_diff, prev_ind, arr);
            return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
        }
        int op1 = 0, op2 = 0;
        if (arr[ind] - arr[prev_ind] > 0) {
            if (prev_diff == 0) op1 = 1 + solve(ind + 1, 1, ind, arr);
            else return 0;
        }
        if (arr[ind] - arr[prev_ind] < 0) {
            if (prev_diff == 1) op1 = 1 + solve(ind + 1, 0, ind, arr);
            else return 0;
        }
        op2 = solve(ind + 1, prev_diff, prev_ind, arr);
        return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

