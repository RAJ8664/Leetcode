# 3649. Minimum Time To Break Locks I

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-time-to-break-locks-i/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3649-minimum-time-to-break-locks-i){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-time-to-break-locks-i">3649. Minimum Time to Break Locks I</a></h2><h3>Medium</h3><hr><p>Bob is stuck in a dungeon and must break <code>n</code> locks, each requiring some amount of <strong>energy</strong> to break. The required energy for each lock is stored in an array called <code>strength</code> where <code>strength[i]</code> indicates the energy needed to break the <code>i<sup>th</sup></code> lock.</p>

<p>To break a lock, Bob uses a sword with the following characteristics:</p>

<ul>
	<li>The initial energy of the sword is 0.</li>
	<li>The initial factor <code><font face="monospace">x</font></code> by which the energy of the sword increases is 1.</li>
	<li>Every minute, the energy of the sword increases by the current factor <code>x</code>.</li>
	<li>To break the <code>i<sup>th</sup></code> lock, the energy of the sword must reach <strong>at least</strong> <code>strength[i]</code>.</li>
	<li>After breaking a lock, the energy of the sword resets to 0, and the factor <code>x</code> increases by a given value <code>k</code>.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> time in minutes required for Bob to break all <code>n</code> locks and escape the dungeon.</p>

<p>Return the <strong>minimum </strong>time required for Bob to break all <code>n</code> locks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [3,4,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">x</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated x</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 3<sup>rd</sup> Lock</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Break 2<sup>nd</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 4 minutes; thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [2,5,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">x</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated x</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 2<sup>n</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">Break 3<sup>r</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 5 minutes; thus, the answer is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
	<li><code>1 &lt;= K &lt;= 10</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Solution {
    public int findMinimumTime(List<Integer> strength, int k) {
        int n = strength.size(), mini = Integer.MAX_VALUE;

        int total = 1;
        for (int i = 1; i <= n; i++)
            total *= i;

        while (total >= 0) {
            mini = Math.min(mini, solve(strength, k));
            nextPermutation(strength);
            total--;
        }
        return mini;
    }

    private int solve(List<Integer> arr, int k) {
        int ans = 0, currentEnergy = 0, currentFactor = 1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % currentFactor == 0)
                ans += arr.get(i) / currentFactor;
            else
                ans += 1 + (arr.get(i) / currentFactor);
            currentFactor += k;
            currentEnergy = 0;
        }
        return ans;
    }

    private void nextPermutation(List<Integer> nums) {
        int n = nums.size();
        int i = n - 2;
        while (i >= 0 && nums.get(i) >= nums.get(i + 1))
            i--;
        if (i >= 0) {
            int j = n - 1;
            while (nums.get(j) <= nums.get(i))
                j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private void swap(List<Integer> nums, int i, int j) {
        Collections.swap(nums, i, j);
    }

    private void reverse(List<Integer> nums, int start, int end) {
        while (start < end)
            swap(nums, start++, end--);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

