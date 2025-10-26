# 3372. Longest Strictly Increasing Or Strictly Decreasing Subarray

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3372-longest-strictly-increasing-or-strictly-decreasing-subarray){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray">3372. Longest Strictly Increasing or Strictly Decreasing Subarray</a></h2><h3>Easy</h3><hr><p>You are given an array of integers <code>nums</code>. Return <em>the length of the <strong>longest</strong> <span data-keyword="subarray-nonempty">subarray</span> of </em><code>nums</code><em> which is either <strong><span data-keyword="strictly-increasing-array">strictly increasing</span></strong> or <strong><span data-keyword="strictly-decreasing-array">strictly decreasing</span></strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The strictly increasing subarrays of <code>nums</code> are <code>[1]</code>, <code>[2]</code>, <code>[3]</code>, <code>[3]</code>, <code>[4]</code>, and <code>[1,4]</code>.</p>

<p>The strictly decreasing subarrays of <code>nums</code> are <code>[1]</code>, <code>[2]</code>, <code>[3]</code>, <code>[3]</code>, <code>[4]</code>, <code>[3,2]</code>, and <code>[4,3]</code>.</p>

<p>Hence, we return <code>2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The strictly increasing subarrays of <code>nums</code> are <code>[3]</code>, <code>[3]</code>, <code>[3]</code>, and <code>[3]</code>.</p>

<p>The strictly decreasing subarrays of <code>nums</code> are <code>[3]</code>, <code>[3]</code>, <code>[3]</code>, and <code>[3]</code>.</p>

<p>Hence, we return <code>1</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The strictly increasing subarrays of <code>nums</code> are <code>[3]</code>, <code>[2]</code>, and <code>[1]</code>.</p>

<p>The strictly decreasing subarrays of <code>nums</code> are <code>[3]</code>, <code>[2]</code>, <code>[1]</code>, <code>[3,2]</code>, <code>[2,1]</code>, and <code>[3,2,1]</code>.</p>

<p>Hence, we return <code>3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        return Math.max(si(nums) , sd(nums));
    }
    public static int si(int arr[]) {
        int n = arr.length;
        int count = 0, maxi = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = i; j < n; j++) {
                if (j == i) count++;
                else {
                    if(arr[j] > arr[j - 1]) count++;
                    else break;
                }
                maxi = Math.max(maxi,  count);
            }
        }
        return maxi;
    }
    public static int sd(int arr[]) {
        int n = arr.length;
        int count = 0, maxi = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = i; j < n; j++) {
                if (j == i) count++;
                else {
                    if(arr[j] < arr[j - 1]) count++;
                    else break;
                }
                maxi = Math.max(maxi, count);
            }
        }
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

