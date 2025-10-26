# 3380. Shortest Subarray With Or At Least K Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3380-shortest-subarray-with-or-at-least-k-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii">3380. Shortest Subarray With OR at Least K II</a></h2><h3>Medium</h3><hr><p>You are given an array <code>nums</code> of <strong>non-negative</strong> integers and an integer <code>k</code>.</p>

<p>An array is called <strong>special</strong> if the bitwise <code>OR</code> of all of its elements is <strong>at least</strong> <code>k</code>.</p>

<p>Return <em>the length of the <strong>shortest</strong> <strong>special</strong> <strong>non-empty</strong> <span data-keyword="subarray-nonempty">subarray</span> of</em> <code>nums</code>, <em>or return</em> <code>-1</code> <em>if no special subarray exists</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[3]</code> has <code>OR</code> value of <code>3</code>. Hence, we return <code>1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,8], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[2,1,8]</code> has <code>OR</code> value of <code>11</code>. Hence, we return <code>3</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[1]</code> has <code>OR</code> value of <code>1</code>. Hence, we return <code>1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n  = nums.length;
        int pref[][] = new int[n + 1][33];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 32; j++) pref[i + 1][j] = pref[i][j] + ((nums[i] >> j) & 1);
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int low = i;
            int high = n - 1;
            while(low <= high) {
                int mid = low + (high - low) / 2;
                if(check(i, mid , pref, k)) {
                    ans = Math.min(ans, mid - i + 1);
                    high = mid - 1;
                }
                else low = mid + 1;
            }
        }
        if(ans == Integer.MAX_VALUE) return -1;
        return ans;
    }

    public static boolean check(int start, int end, int pref[][], int k) {
        int n = pref.length;
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int temp = pref[end + 1][i] - pref[start][i];
            if(temp > 0) res += (1 << i);
        }
        return res >= k;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

