# 3466. Number Of Subarrays With And Value Of K

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-subarrays-with-and-value-of-k/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3466-number-of-subarrays-with-and-value-of-k){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-subarrays-with-and-value-of-k">3466. Number of Subarrays With AND Value of K</a></h2><h3>Hard</h3><hr><p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return the number of <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code> where the bitwise <code>AND</code> of the elements of the subarray equals <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>All subarrays contain only 1&#39;s.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays having an <code>AND</code> value of 1 are: <code>[<u><strong>1</strong></u>,1,2]</code>, <code>[1,<u><strong>1</strong></u>,2]</code>, <code>[<u><strong>1,1</strong></u>,2]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays having an <code>AND</code> value of 2 are: <code>[1,<b><u>2</u></b>,3]</code>, <code>[1,<u><strong>2,3</strong></u>]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private int pref[][];
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        pref = new int[n][32];
        build_and_prefix(nums);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int low = i, high = n - 1, ind1 = -1, ind2 = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int curr = compute_and_in_range(i, mid);
                if(curr > k) low = mid + 1;
                else if (curr <= k) {
                    ind1 = mid;
                    high = mid - 1;
                }
            }
            low = i;
            high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int curr = compute_and_in_range(i, mid);
                if (curr >= k) {
                    low = mid + 1;
                    ind2 = mid;
                }
                else if (curr < k) high = mid - 1;
            }
            if(ind1 != -1 && ind2 != -1) ans += (ind2 - ind1 + 1);
        }
        return ans;
    }

    private void build_and_prefix(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            for (int j = 0; j < 32; j++) {
                int current_bit = ((current >> j) & 1);
                if (i == 0) {
                    if (current_bit > 0) pref[i][j] = 1;
                    else pref[i][j] = 0;
                }
                else { 
                    if (current_bit > 0) pref[i][j] += pref[i - 1][j] + 1;
                    else pref[i][j] = pref[i - 1][j];
                }
            }
        }
    }

    private int compute_and_in_range(int l, int r) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = pref[r][i];
            if (l - 1 >= 0) count -= pref[l - 1][i];
            if (count == r - l + 1) res |= (1 << i);
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

