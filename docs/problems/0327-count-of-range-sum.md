# 327. Count Of Range Sum

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-of-range-sum/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0327-count-of-range-sum){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-of-range-sum">327. Count of Range Sum</a></h2><h3>Hard</h3><hr><p>Given an integer array <code>nums</code> and two integers <code>lower</code> and <code>upper</code>, return <em>the number of range sums that lie in</em> <code>[lower, upper]</code> <em>inclusive</em>.</p>

<p>Range sum <code>S(i, j)</code> is defined as the sum of the elements in <code>nums</code> between indices <code>i</code> and <code>j</code> inclusive, where <code>i &lt;= j</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,5,-1], lower = -2, upper = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0], lower = 0, upper = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>-10<sup>5</sup> &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
	<li>The answer is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>


---

## Solution

```java
public class Solution {
    private int count;
    private int lower;
    private int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        long[] temp = new long[nums.length + 1];
        sum[0] = 0;
        this.lower = lower;
        this.upper = upper;
        count = 0;
        for (int i = 1; i <= nums.length; i++) sum[i] = sum[i - 1] + (long)(nums[i - 1]);
        mergesort(sum, 0, sum.length - 1, temp);
        return count;
    }
    private void mergesort(long[] sum, int start, int end, long[] temp) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        mergesort(sum, start, mid, temp);
        mergesort(sum, mid + 1, end, temp);
        merge(sum, start, mid, end, temp);
    }
    private void merge(long[] sum, int start, int mid, int end, long[] temp) {
        int right = mid + 1, index = start, low = mid + 1, high = mid + 1;
        for (int left = start; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) low++;
            while (high <= end && sum[high] - sum[left] <= upper) high++;
            while (right <= end && sum[right] < sum[left]) temp[index++] = sum[right++];
            temp[index++] = sum[left];
            count += high - low;
        }
        while (right <= end) temp[index++] = sum[right++];
        for (int i = start; i <= end; i++) sum[i] = temp[i];
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

