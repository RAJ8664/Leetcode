# 831. Largest Sum Of Averages

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-sum-of-averages/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0831-largest-sum-of-averages){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-sum-of-averages">831. Largest Sum of Averages</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You can partition the array into <strong>at most</strong> <code>k</code> non-empty adjacent subarrays. The <strong>score</strong> of a partition is the sum of the averages of each subarray.</p>

<p>Note that the partition must use every integer in <code>nums</code>, and that the score is not necessarily an integer.</p>

<p>Return <em>the maximum <strong>score</strong> you can achieve of all the possible partitions</em>. Answers within <code>10<sup>-6</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,1,2,3,9], k = 3
<strong>Output:</strong> 20.00000
<strong>Explanation:</strong> 
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7], k = 4
<strong>Output:</strong> 20.50000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    private double dp[][];
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        dp = new double[n + 1][k + 1];
        for (double current[] : dp) Arrays.fill(current, Double.MIN_VALUE / 10.0);
        return solve(0, k, nums);
    }
    private double solve(int ind, int k, int arr[]) {
        if (ind == arr.length) return 0;
        if (dp[ind][k] != Double.MIN_VALUE / 10.0) return dp[ind][k];
        double sum = 0;
        if (k == 1) {
            for (int i = ind; i < arr.length; i++) sum += arr[i];
            return dp[ind][k] = sum / (arr.length - ind);
        }
        double maxi = 0.0, count = 0.0;
        for (int i = ind; i < arr.length; i++) {
            sum += arr[i];
            count++;
            double current = sum / count;
            maxi = Math.max(maxi, current + solve(i + 1, k - 1, arr));
        }
        return dp[ind][k] = maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

