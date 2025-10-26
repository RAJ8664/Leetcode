# 2478. Longest Nice Subarray

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-nice-subarray/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2478-longest-nice-subarray){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-nice-subarray">2478. Longest Nice Subarray</a></h2><h3>Medium</h3><hr><p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>We call a subarray of <code>nums</code> <strong>nice</strong> if the bitwise <strong>AND</strong> of every pair of elements that are in <strong>different</strong> positions in the subarray is equal to <code>0</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> nice subarray</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p><strong>Note</strong> that subarrays of length <code>1</code> are always considered nice.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,8,48,10]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,11,13]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }
    private boolean ok(int mid, int arr[]) {
        int n = arr.length;
        int freq[] = new int[33];
        for (int i = 0; i < mid; i++) {
            int current = arr[i];
            for (int j = 0; j < 32; j++) {
                int bit = ((current >> j) & 1);
                if (bit > 0) freq[j]++;
            }
        }
        boolean flag = true;
        for (int i = 0; i <= 32; i++) {
            if (freq[i] > 1) flag = false;
        }
        if (flag == true) return true;
        int start = 0;
        for (int i = mid; i < n; i++) {
            int last = arr[start], current = arr[i];
            flag = true;
            for (int j = 0; j < 32; j++) {
                int bit1 = ((last >> j) & 1);
                int bit2 = ((current >> j) & 1);
                if (bit1 > 0) freq[j]--;
                if (bit2 > 0) freq[j]++;
                if (freq[j] > 1) flag = false;
            }
            if (flag == true) return true;
            start++;
        }
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

