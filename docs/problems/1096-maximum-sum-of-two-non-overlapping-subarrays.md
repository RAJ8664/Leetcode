# 1096. Maximum Sum Of Two Non Overlapping Subarrays

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1096-maximum-sum-of-two-non-overlapping-subarrays){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays">1096. Maximum Sum of Two Non-Overlapping Subarrays</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code> and two integers <code>firstLen</code> and <code>secondLen</code>, return <em>the maximum sum of elements in two non-overlapping <strong>subarrays</strong> with lengths </em><code>firstLen</code><em> and </em><code>secondLen</code>.</p>

<p>The array with length <code>firstLen</code> could occur before or after the array with length <code>secondLen</code>, but they have to be non-overlapping.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> One choice of subarrays is [9] with length 1, and [6,5] with length 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
<strong>Output:</strong> 29
<strong>Explanation:</strong> One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
<strong>Output:</strong> 31
<strong>Explanation:</strong> One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= firstLen, secondLen &lt;= 1000</code></li>
	<li><code>2 &lt;= firstLen + secondLen &lt;= 1000</code></li>
	<li><code>firstLen + secondLen &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int current_maxi = 0, current_sum = 0, start = 0;
        for (int i = 0; i < firstLen; i++) current_sum += nums[i];
        if (firstLen + secondLen < n) current_maxi = Math.max(current_maxi, current_sum + solve(nums, firstLen + 1, n - 1, secondLen));
        for (int i = firstLen; i < n; i++) {
            current_sum += nums[i];
            current_sum -= nums[start++];
            if (start >= secondLen) current_maxi = Math.max(current_maxi, current_sum + solve(nums, 0, start - 1, secondLen));
            if (i + 1 + secondLen < n) current_maxi = Math.max(current_maxi, current_sum + solve(nums, i + 1, n - 1, secondLen));
        }
        return current_maxi;
    }
    private int solve(int arr[] , int low, int high, int len) {
        int maxi_sum = 0, current_sum = 0, start = low;
        for (int i = low; i < low + len; i++) current_sum += arr[i];
        maxi_sum = Math.max(maxi_sum , current_sum);
        for (int i = low + len; i <= high; i++) {
            current_sum += arr[i];
            current_sum -= arr[start++];
            maxi_sum = Math.max(maxi_sum, current_sum);
        }
        return maxi_sum;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

