# 3619. Adjacent Increasing Subarrays Detection Ii


---

<h2><a href="https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii">3619. Adjacent Increasing Subarrays Detection II</a></h2><h3>Medium</h3><hr><p>Given an array <code>nums</code> of <code>n</code> integers, your task is to find the <strong>maximum</strong> value of <code>k</code> for which there exist <strong>two</strong> adjacent subarrays of length <code>k</code> each, such that both subarrays are <strong>strictly</strong> <strong>increasing</strong>. Specifically, check if there are <strong>two</strong> subarrays of length <code>k</code> starting at indices <code>a</code> and <code>b</code> (<code>a &lt; b</code>), where:</p>

<ul>
	<li>Both subarrays <code>nums[a..a + k - 1]</code> and <code>nums[b..b + k - 1]</code> are <strong>strictly increasing</strong>.</li>
	<li>The subarrays must be <strong>adjacent</strong>, meaning <code>b = a + k</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> <em>possible</em> value of <code>k</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <b>non-empty</b> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,7,8,9,2,3,4,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray starting at index 2 is <code>[7, 8, 9]</code>, which is strictly increasing.</li>
	<li>The subarray starting at index 5 is <code>[2, 3, 4]</code>, which is also strictly increasing.</li>
	<li>These two subarrays are adjacent, and 3 is the <strong>maximum</strong> possible value of <code>k</code> for which two such adjacent strictly increasing subarrays exist.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,4,4,4,5,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray starting at index 0 is <code>[1, 2]</code>, which is strictly increasing.</li>
	<li>The subarray starting at index 2 is <code>[3, 4]</code>, which is also strictly increasing.</li>
	<li>These two subarrays are adjacent, and 2 is the <strong>maximum</strong> possible value of <code>k</code> for which two such adjacent strictly increasing subarrays exist.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


## Solution

```java
class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int isincreasing[] = new int[n];
        Arrays.fill(isincreasing, 1);
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1)) isincreasing[i] = isincreasing[i + 1] + 1;
        }
        int low = 0;
        int high = n / 2 + 1;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean flag = false;
            for (int i = 0; i + 2 * mid <= n; i++) {
                if (isincreasing[i] >= mid && isincreasing[i + mid] >= mid) {
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    private boolean check(List<Integer> nums, int start, int end, int k) {
        int n = nums.size();
        int prev = -2000;        
        for (int i = start; i < start + k; i++) {
            if (prev == -2000) {
                prev = nums.get(i);
            }
            else {
                if (nums.get(i) <= prev) return false;
                prev = nums.get(i);
            }
        }
        prev = -2000;
        for (int i = start + k; i < end; i++) {
            if (prev == -2000) {
                prev = nums.get(i);
            }
            else {
                if (nums.get(i) <= prev) return false;
                prev = nums.get(i);
            }
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

