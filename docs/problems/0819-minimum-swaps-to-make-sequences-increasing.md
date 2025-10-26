# 819. Minimum Swaps To Make Sequences Increasing

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0819-minimum-swaps-to-make-sequences-increasing){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing">819. Minimum Swaps To Make Sequences Increasing</a></h2><h3>Hard</h3><hr><p>You are given two integer arrays of the same length <code>nums1</code> and <code>nums2</code>. In one operation, you are allowed to swap <code>nums1[i]</code> with <code>nums2[i]</code>.</p>

<ul>
	<li>For example, if <code>nums1 = [1,2,3,<u>8</u>]</code>, and <code>nums2 = [5,6,7,<u>4</u>]</code>, you can swap the element at <code>i = 3</code> to obtain <code>nums1 = [1,2,3,4]</code> and <code>nums2 = [5,6,7,8]</code>.</li>
</ul>

<p>Return <em>the minimum number of needed operations to make </em><code>nums1</code><em> and </em><code>nums2</code><em> <strong>strictly increasing</strong></em>. The test cases are generated so that the given input always makes it possible.</p>

<p>An array <code>arr</code> is <strong>strictly increasing</strong> if and only if <code>arr[0] &lt; arr[1] &lt; arr[2] &lt; ... &lt; arr[arr.length - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,3,5,4], nums2 = [1,2,3,7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
Swap nums1[3] and nums2[3]. Then the sequences are:
nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
which are both strictly increasing.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int dp[][];
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        dp = new int[n + 1][2];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(nums1, nums2, -1, -1, 0, 0);
    }
    private int solve(int arr[], int brr[], int prevA, int prevB, int ind, int swap) {
        if (ind >= arr.length)
            return 0;
        if (dp[ind][swap] != -1)
            return dp[ind][swap];

        int mini = Integer.MAX_VALUE / 10;
        if (arr[ind] > prevA && brr[ind] > prevB)
            mini = Math.min(mini, 0 + solve(arr, brr, arr[ind], brr[ind], ind + 1, 0));
        if (arr[ind] > prevB && brr[ind] > prevA)
            mini = Math.min(mini, 1 + solve(arr, brr, brr[ind], arr[ind], ind + 1, 1));

        return dp[ind][swap] = mini;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

