# 4. Median Of Two Sorted Arrays

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/median-of-two-sorted-arrays/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0004-median-of-two-sorted-arrays){ .md-button }

---

<h2><a href="https://leetcode.com/problems/median-of-two-sorted-arrays">4. Median of Two Sorted Arrays</a></h2><h3>Hard</h3><hr><p>Given two sorted arrays <code>nums1</code> and <code>nums2</code> of size <code>m</code> and <code>n</code> respectively, return <strong>the median</strong> of the two sorted arrays.</p>

<p>The overall run time complexity should be <code>O(log (m+n))</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,3], nums2 = [2]
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> merged array = [1,2,3] and median is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2], nums2 = [3,4]
<strong>Output:</strong> 2.50000
<strong>Explanation:</strong> merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int first = -1, second = -1;
        int i = 0, j = 0;
        int count = -1;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                count++;
                if (count == (n + m) / 2) {
                    first = nums1[i];
                }
                else if (count == (n + m) / 2 - 1) {
                    second = nums1[i];
                }
                i++;
            }
            else {
                count++;
                if (count == (n + m) / 2) {
                    first = nums2[j];
                }
                else if (count == (n + m) / 2 - 1) {
                    second = nums2[j];
                }
                j++;
            }
        }
        while (i < n) {
            count++;
            if (count == (n + m) / 2) {
                first = nums1[i];
            }
            else if (count == (n + m) / 2 - 1) {
                second = nums1[i];
            }
            i++;
        }
        while (j < m) {
            count++;
            if (count == (n + m) / 2) {
                first = nums2[j];
            }
            else if (count == (n + m) / 2 - 1) {
                second = nums2[j];
            }
            j++;
        }
        if ((n + m) % 2 == 0) {
            return ((first * 1.0 + second * 1.0) / 2);
        }
        return first;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

