# 594. Longest Harmonious Subsequence

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-harmonious-subsequence/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0594-longest-harmonious-subsequence){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-harmonious-subsequence">594. Longest Harmonious Subsequence</a></h2><h3>Easy</h3><hr><p>We define a harmonious array as an array where the difference between its maximum value and its minimum value is <b>exactly</b> <code>1</code>.</p>

<p>Given an integer array <code>nums</code>, return the length of its longest harmonious <span data-keyword="subsequence-array">subsequence</span> among all its possible subsequences.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,2,5,2,3,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest harmonious subsequence is <code>[3,2,2,2,3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest harmonious subsequences are <code>[1,2]</code>, <code>[2,3]</code>, and <code>[3,4]</code>, all of which have a length of 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No harmonic subsequence exists.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int findLHS(int[] nums) {
        int n = nums.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
        }
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            TreeSet<Integer> current = map.get(nums[i] + 1);
            if (current != null && current.size() > 0) {
                maxi = Math.max(maxi, current.last() - i + 1);
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

