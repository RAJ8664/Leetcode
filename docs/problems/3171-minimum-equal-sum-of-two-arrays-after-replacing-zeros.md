# 3171. Minimum Equal Sum Of Two Arrays After Replacing Zeros

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3171-minimum-equal-sum-of-two-arrays-after-replacing-zeros){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros">3171. Minimum Equal Sum of Two Arrays After Replacing Zeros</a></h2><h3>Medium</h3><hr><p>You are given two arrays <code>nums1</code> and <code>nums2</code> consisting of positive integers.</p>

<p>You have to replace <strong>all</strong> the <code>0</code>&#39;s in both arrays with <strong>strictly</strong> positive integers such that the sum of elements of both arrays becomes <strong>equal</strong>.</p>

<p>Return <em>the <strong>minimum</strong> equal sum you can obtain, or </em><code>-1</code><em> if it is impossible</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,2,0,1,0], nums2 = [6,5,0]
<strong>Output:</strong> 12
<strong>Explanation:</strong> We can replace 0&#39;s in the following way:
- Replace the two 0&#39;s in nums1 with the values 2 and 4. The resulting array is nums1 = [3,2,2,1,4].
- Replace the 0 in nums2 with the value 1. The resulting array is nums2 = [6,5,1].
Both arrays have an equal sum of 12. It can be shown that it is the minimum sum we can obtain.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,0,2,0], nums2 = [1,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to make the sum of both arrays equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        long count1 = 0, count2 = 0;
        long sum1 = 0, sum2 = 0;
        for (int ele : nums1) {
            sum1 += ele;
            if (ele == 0) count1++;
        } 
        for (int ele : nums2) {
            sum2 += ele;
            if (ele == 0) count2++;
        }
        if (sum1 > sum2) {
            long diff = sum1 - sum2;
            if (count2 == 0) return -1;
            if (count1 == 0 && sum2 + count2 > sum1) return -1;
            else {
                return (Math.max(sum1 + count1, sum2 + count2));
            }
        }
        else if (sum2 > sum1) {
            long diff = sum2 - sum2;
            if (count1 == 0) return -1;
            if (count2 == 0 && sum1 + count1 > sum2) return -1;
            else {
                return (Math.max(sum2 + count2, sum1 + count1));
            }
        }
        else if (sum1 == sum2) {
            if (count1 == 0 && count2 == 0) return sum1;
            else if (count1 > 0 && count2 == 0) return -1;
            else if (count2 > 0 && count1 == 0) return -1;
            return (Math.max(sum1 + count1, sum2 + count2));
        }
        return -1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

