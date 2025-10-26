# 2150. Kth Smallest Product Of Two Sorted Arrays

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2150-kth-smallest-product-of-two-sorted-arrays){ .md-button }

---

<h2><a href="https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays">2150. Kth Smallest Product of Two Sorted Arrays</a></h2><h3>Hard</h3><hr>Given two <strong>sorted 0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> as well as an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> (<strong>1-based</strong>) smallest product of </em><code>nums1[i] * nums2[j]</code><em> where </em><code>0 &lt;= i &lt; nums1.length</code><em> and </em><code>0 &lt;= j &lt; nums2.length</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,5], nums2 = [3,4], k = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> The 2 smallest products are:
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
The 2<sup>nd</sup> smallest product is 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> The 6 smallest products are:
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
The 6<sup>th</sup> smallest product is 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
<strong>Output:</strong> -6
<strong>Explanation:</strong> The 3 smallest products are:
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
The 3<sup>rd</sup> smallest product is -6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums1.length * nums2.length</code></li>
	<li><code>nums1</code> and <code>nums2</code> are sorted.</li>
</ul>


---

## Solution

```java
class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int n = nums1.length, m = nums2.length;
        long low = -((long)(1e12)), high = (long)(1e12);
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(nums1, nums2, mid, k))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
    private boolean ok(int arr[], int brr[], long req, long k) {
        int n = arr.length, m = brr.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (req >= 0)
                    count += m;
                continue;
            } else if (arr[i] < 0) {
                int low = 0, high = m - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (arr[i] * 1L * brr[mid] <= req)
                        high = mid - 1;
                    else
                        low = mid + 1;
                }
                count += m - low;
            } else {
                int low = 0, high = m - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (arr[i] * 1L * brr[mid] <= req)
                        low = mid + 1;
                    else
                        high = mid - 1;
                }
                count += low;
            }
        }
        return count < k;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

