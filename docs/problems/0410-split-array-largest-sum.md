# 410. Split Array Largest Sum

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/split-array-largest-sum/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0410-split-array-largest-sum){ .md-button }

---

<h2><a href="https://leetcode.com/problems/split-array-largest-sum">410. Split Array Largest Sum</a></h2><h3>Hard</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, split <code>nums</code> into <code>k</code> non-empty subarrays such that the largest sum of any subarray is <strong>minimized</strong>.</p>

<p>Return <em>the minimized largest sum of the split</em>.</p>

<p>A <strong>subarray</strong> is a contiguous part of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,2,5,10,8], k = 2
<strong>Output:</strong> 18
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(50, nums.length)</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int low = 0, high = (int)(1e9), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        } 
        return ans;
    }
    private boolean ok(int mid, int arr[], int k) {
        int n = arr.length;
        long currSum = 0, count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > mid) {
                return false;
            }
            if (currSum + arr[i] > mid) {
                currSum = arr[i];
                count++;
            }
            else {
                currSum += arr[i];
            }
        }
        
        return count + 1 <= k;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

