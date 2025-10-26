# 2604. Minimum Operations To Make Array Equal Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-operations-to-make-array-equal-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2604-minimum-operations-to-make-array-equal-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-operations-to-make-array-equal-ii">2604. Minimum Operations to Make Array Equal II</a></h2><h3>Medium</h3><hr><p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of equal length <code>n</code> and an integer <code>k</code>. You can perform the following operation on <code>nums1</code>:</p>

<ul>
	<li>Choose two indexes <code>i</code> and <code>j</code> and increment <code>nums1[i]</code> by <code>k</code> and decrement <code>nums1[j]</code> by <code>k</code>. In other words, <code>nums1[i] = nums1[i] + k</code> and <code>nums1[j] = nums1[j] - k</code>.</li>
</ul>

<p><code>nums1</code> is said to be <strong>equal</strong> to <code>nums2</code> if for all indices <code>i</code> such that <code>0 &lt;= i &lt; n</code>, <code>nums1[i] == nums2[i]</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of operations required to make </em><code>nums1</code><em> equal to </em><code>nums2</code>. If it is impossible to make them equal, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 operations, we can transform nums1 to nums2.
1<sup>st</sup> operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
2<sup>nd</sup> operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
One can prove that it is impossible to make arrays equal in fewer operations.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proved that it is impossible to make the two arrays equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        if (k == 0) {
            for (int i = 0; i < n; i++) {
                if (nums1[i] != nums2[i])
                    return -1;
            }
            return 0;
        }
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums1[i] - nums2[i]) % k != 0)
                return -1;
        }
        long totalInc = 0, totalDec = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] > nums2[i])
                totalDec += 1L * (nums1[i] - nums2[i]) / k;
            else
                totalInc += 1L * (nums2[i] - nums1[i]) / k;
        }
        if (totalInc != totalDec)
            return -1;
        return totalDec;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

