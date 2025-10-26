# 1966. Frequency Of The Most Frequent Element

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/frequency-of-the-most-frequent-element/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1966-frequency-of-the-most-frequent-element){ .md-button }

---

<h2><a href="https://leetcode.com/problems/frequency-of-the-most-frequent-element">1966. Frequency of the Most Frequent Element</a></h2><h3>Medium</h3><hr><p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

<p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 5
<strong>Output:</strong> 3<strong>
Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,8,13], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,6], k = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int low = 1, high = n, ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }   
        return ans;
    }

    private boolean ok(int mid, int arr[], int k) {
        int n = arr.length;
        long current_sum = 0;
        for (int i = 0; i < mid; i++) current_sum += arr[i];
        long req = arr[mid - 1] * 1L * mid;
        long left = req - current_sum;
        if (left  <= k) return true;
        int start = 0;
        for (int i = mid; i < n; i++) {
            current_sum += arr[i];
            current_sum -= arr[start];
            req = arr[i] * 1L * mid;
            left = req - current_sum;
            if (left <= k) return true;
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

