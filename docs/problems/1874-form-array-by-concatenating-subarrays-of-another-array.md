# 1874. Form Array By Concatenating Subarrays Of Another Array

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1874-form-array-by-concatenating-subarrays-of-another-array){ .md-button }

---

<h2><a href="https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array">1874. Form Array by Concatenating Subarrays of Another Array</a></h2><h3>Medium</h3><hr><p>You are given a 2D integer array <code>groups</code> of length <code>n</code>. You are also given an integer array <code>nums</code>.</p>

<p>You are asked if you can choose <code>n</code> <strong>disjoint </strong>subarrays from the array <code>nums</code> such that the <code>i<sup>th</sup></code> subarray is equal to <code>groups[i]</code> (<b>0-indexed</b>), and if <code>i &gt; 0</code>, the <code>(i-1)<sup>th</sup></code> subarray appears <strong>before</strong> the <code>i<sup>th</sup></code> subarray in <code>nums</code> (i.e. the subarrays must be in the same order as <code>groups</code>).</p>

<p>Return <code>true</code> <em>if you can do this task, and</em> <code>false</code> <em>otherwise</em>.</p>

<p>Note that the subarrays are <strong>disjoint</strong> if and only if there is no index <code>k</code> such that <code>nums[k]</code> belongs to more than one subarray. A subarray is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> You can choose the 0<sup>th</sup> subarray as [1,-1,0,<u><strong>1,-1,-1</strong></u>,3,-2,0] and the 1<sup>st</sup> one as [1,-1,0,1,-1,-1,<u><strong>3,-2,0</strong></u>].
These subarrays are disjoint as they share no common nums[k] element.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
<strong>Output:</strong> false
<strong>Explanation: </strong>Note that choosing the subarrays [<u><strong>1,2,3,4</strong></u>,10,-2] and [1,2,3,4,<u><strong>10,-2</strong></u>] is incorrect because they are not in the same order as in groups.
[10,-2] must come before [1,2,3,4].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
<strong>Output:</strong> false
<strong>Explanation: </strong>Note that choosing the subarrays [7,7,<u><strong>1,2,3</strong></u>,4,7,7] and [7,7,1,2,<u><strong>3,4</strong></u>,7,7] is invalid because they are not disjoint.
They share a common elements nums[4] (0-indexed).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>groups.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= groups[i].length, sum(groups[i].length) &lt;= 10<sup><span style="font-size: 10.8333px;">3</span></sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= groups[i][j], nums[k] &lt;= 10<sup>7</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = groups.length, m = groups[0].length;
        int currIdx = 0;
        RangeHash Hash = new RangeHash(nums);
        for (int i = 0; i < n; i++) {
            RangeHash reqHash = new RangeHash(groups[i]);
            boolean flag = false;
            for (int j = currIdx; j < nums.length; j++) {
                if (j + groups[i].length - 1 < nums.length) {
                    if (Hash.getRangeHash(j, j + groups[i].length - 1) == reqHash.getRangeHash(0, groups[i].length - 1)) {
                        currIdx = j + groups[i].length;
                        flag = true;
                        break;
                    }
                }
            }
            if (flag == false)
                return false;
        }
        return true;
    }
    public class RangeHash {
        private long[] prefixHash;
        private long[] powBase;
        private final int base = 31;
        private final int mod = 1_000_000_007;

        public RangeHash(int[] arr) {
            int n = arr.length;
            prefixHash = new long[n + 1];
            powBase = new long[n + 1];
            powBase[0] = 1;
            for (int i = 0; i < n; i++) {
                prefixHash[i + 1] = (prefixHash[i] * base + arr[i]) % mod;
                powBase[i + 1] = (powBase[i] * base) % mod;
            }
        }

        public long getRangeHash(int l, int r) {
            long hash = (prefixHash[r + 1] - prefixHash[l] * powBase[r - l + 1] % mod + mod) % mod;
            return hash;
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

