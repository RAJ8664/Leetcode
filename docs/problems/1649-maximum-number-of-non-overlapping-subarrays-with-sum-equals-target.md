# 1649. Maximum Number Of Non Overlapping Subarrays With Sum Equals Target

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1649-maximum-number-of-non-overlapping-subarrays-with-sum-equals-target){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target">1649. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target</a></h2><h3>Medium</h3><hr><p>Given an array <code>nums</code> and an integer <code>target</code>, return <em>the maximum number of <strong>non-empty</strong> <strong>non-overlapping</strong> subarrays such that the sum of values in each subarray is equal to</em> <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 non-overlapping subarrays [<strong>1,1</strong>,1,<strong>1,1</strong>] with sum equals to target(2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,3,5,1,4,2,-9], target = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 3 subarrays with sum equal to 6.
([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefSum = 0, count = 0;
        for (int i = 0; i < n; i++) {
            prefSum += nums[i];
            if (prefSum == target) {
                count++;
                prefSum = 0;
                map.clear();
            }
            else {
                int req = prefSum - target;
                if (map.containsKey(req)) {
                    count++;
                    prefSum = 0;
                    map.clear();
                }
                else {
                    if (!map.containsKey(prefSum)) 
                        map.put(prefSum, i);
                }
            } 
        }       
        return count; 
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

