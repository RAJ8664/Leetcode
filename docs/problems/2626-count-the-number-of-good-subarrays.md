# 2626. Count The Number Of Good Subarrays

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-the-number-of-good-subarrays/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2626-count-the-number-of-good-subarrays){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-the-number-of-good-subarrays">2626. Count the Number of Good Subarrays</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>good</strong> subarrays of</em> <code>nums</code>.</p>

<p>A subarray <code>arr</code> is <strong>good</strong> if there are <strong>at least </strong><code>k</code> pairs of indices <code>(i, j)</code> such that <code>i &lt; j</code> and <code>arr[i] == arr[j]</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], k = 10
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only good subarray is the array nums itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,3,2,2,4], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long countGood(int[] nums, int k) {
        long res = 0L;
        Map<Integer, Integer> count = new HashMap<>();
        for(int i = 0, j = 0; j < nums.length; ++j){
            k -= count.getOrDefault(nums[j],0);
            count.put(nums[j],count.getOrDefault(nums[j],0)+1);
            while(k <= 0){
                count.put(nums[i],count.get(nums[i])-1);
                k += count.get(nums[i++]);
            }
            res += i;
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

