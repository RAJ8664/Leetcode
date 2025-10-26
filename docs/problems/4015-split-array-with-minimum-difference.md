# 4015. Split Array With Minimum Difference


---

<h2><a href="https://leetcode.com/problems/split-array-with-minimum-difference">4015. Split Array With Minimum Difference</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code>.</p>

<p>Split the array into <strong>exactly</strong> two <span data-keyword="subarray-nonempty">subarrays</span>, <code>left</code> and <code>right</code>, such that <code>left</code> is <strong><span data-keyword="strictly-increasing-array">strictly increasing</span> </strong> and <code>right</code> is <strong><span data-keyword="strictly-decreasing-array">strictly decreasing</span></strong>.</p>

<p>Return the <strong>minimum possible absolute difference</strong> between the sums of <code>left</code> and <code>right</code>. If no valid split exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[3, 2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;"><code>|1 - 5| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>|4 - 2| = 2</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[2, 4, 3]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[4, 3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;"><code>|3 - 7| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[1, 2, 4]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>|7 - 3| = 4</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid split exists, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solution

```java
class Solution {
    private boolean isInc[], isDec[];
    private long pref[], suff[];
    public long splitArray(int[] nums) {
        int n = nums.length;

        pref = new long[n];
        suff = new long[n];
        isInc = new boolean[n];
        isDec = new boolean[n];

        pref[0] = nums[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + nums[i];
        suff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) 
            suff[i] = suff[i + 1] + nums[i];
        isInc[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) 
                isInc[i] = isInc[i - 1] & true;
            else 
                isInc[i] = false;
        } 
        isDec[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) 
                isDec[i] = isDec[i + 1] & true;
            else 
                isDec[i] = false;
        }

        long mini = Long.MAX_VALUE / 10;;
        for (int i = 0; i < n - 1; i++) {
            if (isInc[i] == true && isDec[i + 1] == true) 
                mini = Math.min(mini, Math.abs(pref[i] - suff[i + 1]));
        }
        return mini == Long.MAX_VALUE / 10 ? -1 : mini; 
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

