# 689. Maximum Sum Of 3 Non Overlapping Subarrays

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0689-maximum-sum-of-3-non-overlapping-subarrays){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays">689. Maximum Sum of 3 Non-Overlapping Subarrays</a></h2><h3>Hard</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, find three non-overlapping subarrays of length <code>k</code> with maximum sum and return them.</p>

<p>Return the result as a list of indices representing the starting position of each interval (<strong>0-indexed</strong>). If there are multiple answers, return the lexicographically smallest one.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2,6,7,5,1], k = 2
<strong>Output:</strong> [0,3,5]
<strong>Explanation:</strong> Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2,1,2,1,2,1], k = 2
<strong>Output:</strong> [0,2,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;&nbsp;2<sup>16</sup></code></li>
	<li><code>1 &lt;= k &lt;= floor(nums.length / 3)</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n - k + 1];
        int windowSum = 0;
        for (int i = 0; i < n; i++) {
            windowSum += nums[i];
            if (i >= k - 1) {
                sums[i - k + 1] = windowSum;
                windowSum -= nums[i - k + 1];
            }
        }
        int[] left = new int[sums.length];
        int leftMaxIndex = 0;
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] > sums[leftMaxIndex]) {
                leftMaxIndex = i;
            }
            left[i] = leftMaxIndex;
        }

        int[] right = new int[sums.length];
        int rightMaxIndex = sums.length - 1;
        for (int i = sums.length - 1; i >= 0; i--) {
            if (sums[i] >= sums[rightMaxIndex]) {
                rightMaxIndex = i;
            }
            right[i] = rightMaxIndex;
        }

        int maxSum = 0;
        int[] result = new int[3];
        for (int mid = k; mid < sums.length - k; mid++) {
            int l = left[mid - k], r = right[mid + k];
            int totalSum = sums[l] + sums[mid] + sums[r];
            if (totalSum > maxSum) {
                maxSum = totalSum;
                result = new int[] {l, mid, r};
            }
        }
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

