# 698. Partition To K Equal Sum Subsets

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0698-partition-to-k-equal-sum-subsets){ .md-button }

---

<h2><a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets">698. Partition to K Equal Sum Subsets</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> if it is possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,3,5,2,1], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>The frequency of each element is in the range <code>[1, 4]</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    private int flag;
    private Map<String, Boolean> memo;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length, sum = 0;
        flag = 0;
        for (int ele : nums) sum += ele;
        if (sum % k != 0) return false;
        int target = sum / k;
        memo = new HashMap<>();
        Arrays.sort(nums);
        reverse(nums);
        return solve(0, 0, nums, new boolean[n], k, target);
    }
    private boolean solve(int curIndex, int curSum, int[] nums, boolean[] visited, int k, int target) {
        if (k == 1) return true; 
        String memoKey = curSum + "-" + k + "-" + java.util.Arrays.toString(visited);
        if (memo.containsKey(memoKey)) return memo.get(memoKey);
        if (curSum == target) {
            boolean result = solve(0, 0, nums, visited, k - 1, target);
            memo.put(memoKey, result);
            return result;
        }
        for (int i = curIndex; i < nums.length; i++) {
            if (!visited[i] && curSum + nums[i] <= target) {
                visited[i] = true;
                if (solve(i + 1, curSum + nums[i], nums, visited, k, target)) {
                    memo.put(memoKey, true);
                    return true;
                }
                visited[i] = false; 
            }
        }
        memo.put(memoKey, false);
        return false;
    }
    private void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

