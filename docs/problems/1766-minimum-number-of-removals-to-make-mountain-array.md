# 1766. Minimum Number Of Removals To Make Mountain Array

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1766-minimum-number-of-removals-to-make-mountain-array){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array">1766. Minimum Number of Removals to Make Mountain Array</a></h2><h3>Hard</h3><hr><p>You may recall that an array <code>arr</code> is a <strong>mountain array</strong> if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some index <code>i</code> (<strong>0-indexed</strong>) with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given an integer array <code>nums</code>​​​, return <em>the <strong>minimum</strong> number of elements to remove to make </em><code>nums<em>​​​</em></code><em> </em><em>a <strong>mountain array</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array itself is a mountain array so we do not need to remove any elements.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,1,5,6,2,3,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that you can make a mountain array out of <code>nums</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        return n - solve(nums);
    }

    public static int solve(int arr[]){
        int n = arr.length;
        int dp1[] = new int[n + 1];
        int dp2[] = new int[n + 2];
        Arrays.fill(dp1,1); Arrays.fill(dp2, 1);
        for(int ind = 0; ind < n; ind ++ ){
            for(int prev = 0; prev < ind; prev ++){
                if(arr[ind] > arr[prev]) dp1[ind] = Math.max(dp1[ind] , 1 + dp1[prev]);
            }
        }
        for(int ind = n -  1; ind >=0 ;ind --){
            for(int prev = n - 1; prev > ind; prev--){
                if(arr[ind] > arr[prev]) dp2[ind] = Math.max(dp2[ind] , 1 + dp2[prev]);
            }
        }
        int maxi = -1;
        for(int i = 0; i < n; i++){
            if(dp1[i] >= 2 && dp2[i] >= 2) maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
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

