# 3491. Find The Maximum Length Of Valid Subsequence Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3491-find-the-maximum-length-of-valid-subsequence-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii">3491. Find the Maximum Length of Valid Subsequence II</a></h2><h3>Medium</h3><hr>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.
<p>A <span data-keyword="subsequence-array">subsequence</span> <code>sub</code> of <code>nums</code> with length <code>x</code> is called <strong>valid</strong> if it satisfies:</p>

<ul>
	<li><code>(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.</code></li>
</ul>
Return the length of the <strong>longest</strong> <strong>valid</strong> subsequence of <code>nums</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 2, 3, 4, 5]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,3,1,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 4, 1, 4]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int dp[][] = new int[n + 1][k + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int sum = (nums[i] + nums[j]) % k;
                dp[i][sum] = Math.max(dp[i][sum] , dp[j][sum] + 1);
            }
        }
        int maxi = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < k; j++) 
                maxi = Math.max(maxi, dp[i][j]);
        }
        return maxi + 1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

