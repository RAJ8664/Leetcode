# 3725. Maximum And Minimum Sums Of At Most Size K Subarrays


---

<h2><a href="https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays">3725. Maximum and Minimum Sums of at Most Size K Subarrays</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>. Return the sum of the <strong>maximum</strong> and <strong>minimum</strong> elements of all <span data-keyword="subarray-nonempty">subarrays</span> with <strong>at most</strong> <code>k</code> elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarrays of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subarray</b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">20</td>
		</tr>
	</tbody>
</table>

<p>The output would be 20.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-3,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-6</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarrays of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subarray</b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[-3]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">-6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, -3]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">-2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[-3, 1]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">-2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">-6</td>
		</tr>
	</tbody>
</table>

<p>The output would be -6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 80000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>


## Solution

```java
class Solution {
    public long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] LMax = new long[n];
        long[] RMax = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]) stack.pop();
            if(stack.isEmpty()) LMax[i] = i + 1;
            else LMax[i] = i - stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.pop();
            if(stack.isEmpty()) RMax[i] = (long) (n - i);
            else RMax[i] = stack.peek() - i;
            stack.push(i);
        }
        long[] LMin = new long[n];
        long[] RMin = new long[n];
        stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) stack.pop();
            if(stack.isEmpty())  LMin[i] = i + 1;
            else  LMin[i] = i - stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i])  stack.pop();
            if(stack.isEmpty()) RMin[i] = (long) (n - i);
            else RMin[i] = stack.peek() - i;
            stack.push(i);
        }
        long ans = 0;
        for(int i = 0; i < n; i++) ans += nums[i] * solve(LMax[i], RMax[i], k) + nums[i] * solve(LMin[i], RMin[i], k);
        return ans;
    }
    public long solve(long L, long R, int k) {        
        if(L <= 0 || R <= 0) return 0;
        long total = 0;
        long X0 = (long)k - R;
        long leftCnt = 0;
        if(X0 >= 0) leftCnt = Math.min(L, X0 + 1);
        total += (long) R * leftCnt;
        long startX = leftCnt;
        long endX = L - 1;
        if(startX <= endX) {
            long realEnd = Math.min(endX, (long)k - 1);
            if(startX <= realEnd) {
                long count = realEnd - startX + 1;
                long a = startX;
                long b = realEnd;
                long sumX = (b * (b + 1) / 2) - ((a - 1) * a / 2);                 
                long temp = (long)k * count - sumX;
                total += temp;
            }
        }
        return Math.max(total, 0);
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

