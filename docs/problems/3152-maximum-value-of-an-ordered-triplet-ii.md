# 3152. Maximum Value Of An Ordered Triplet Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3152-maximum-value-of-an-ordered-triplet-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii">3152. Maximum Value of an Ordered Triplet II</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p>Return <em><strong>the maximum value over all triplets of indices</strong></em> <code>(i, j, k)</code> <em>such that</em> <code>i &lt; j &lt; k</code><em>. </em>If all such triplets have a negative value, return <code>0</code>.</p>

<p>The <strong>value of a triplet of indices</strong> <code>(i, j, k)</code> is equal to <code>(nums[i] - nums[j]) * nums[k]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,6,1,2,7]
<strong>Output:</strong> 77
<strong>Explanation:</strong> The value of the triplet (0, 2, 4) is (nums[0] - nums[2]) * nums[4] = 77.
It can be shown that there are no ordered triplets of indices with a value greater than 77. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,3,4,19]
<strong>Output:</strong> 133
<strong>Explanation:</strong> The value of the triplet (1, 2, 4) is (nums[1] - nums[2]) * nums[4] = 133.
It can be shown that there are no ordered triplets of indices with a value greater than 133.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The only ordered triplet of indices (0, 1, 2) has a negative value of (nums[0] - nums[1]) * nums[2] = -3. Hence, the answer would be 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int pref_maxi[] = new int[n];
        int suff_maxi[] = new int[n];
        int maxi = nums[0];
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, nums[i]);
            pref_maxi[i] = maxi;
        }
        maxi = nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            maxi = Math.max(maxi, nums[i]);
            suff_maxi[i] = maxi;
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i + 2 < n) {
                int left = pref_maxi[i], middle = nums[i + 1], right = suff_maxi[i + 2];
                res = Math.max(res, (left - middle) * 1L * right);
            }
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

