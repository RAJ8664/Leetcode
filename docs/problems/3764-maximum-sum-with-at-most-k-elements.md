# 3764. Maximum Sum With At Most K Elements


---

<h2><a href="https://leetcode.com/problems/maximum-sum-with-at-most-k-elements">3764. Maximum Sum With at Most K Elements</a></h2><h3>Medium</h3><hr><p data-pm-slice="1 3 []">You are given a 2D integer matrix <code>grid</code> of size <code>n x m</code>, an integer array <code>limits</code> of length <code>n</code>, and an integer <code>k</code>. The task is to find the <strong>maximum sum</strong> of <strong>at most</strong> <code>k</code> elements from the matrix <code>grid</code> such that:</p>

<ul data-spread="false">
	<li>
	<p>The number of elements taken from the <code>i<sup>th</sup></code> row of <code>grid</code> does not exceed <code>limits[i]</code>.</p>
	</li>
</ul>

<p data-pm-slice="1 1 []">Return the <strong>maximum sum</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[3,4]], limits = [1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>From the second row, we can take at most 2 elements. The elements taken are 4 and 3.</li>
	<li>The maximum possible sum of at most 2 selected elements is <code>4 + 3 = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">21</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>From the first row, we can take at most 2 elements. The element taken is 7.</li>
	<li>From the second row, we can take at most 2 elements. The elements taken are 8 and 6.</li>
	<li>The maximum possible sum of at most 3 selected elements is <code>7 + 8 + 6 = 21</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == limits.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= limits[i] &lt;= m</code></li>
	<li><code>0 &lt;= k &lt;= min(n * m, sum(limits))</code></li>
</ul>


## Solution

```java
class Solution {
    private long dp[][];
    public long maxSum(int[][] grid, int[] limits, int k) {
        int n = grid.length, m = grid[0].length;
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> r = new ArrayList<>();
            for (int j = 0; j < m; j++) r.add(grid[i][j]);
            Collections.sort(r);
            Collections.reverse(r);
            ArrayList<Integer> r1 = new ArrayList<>();
            for (int ele : r) if (r1.size() < limits[i]) r1.add(ele);
            elements.add(new ArrayList<>(r1));
        }
        for (ArrayList<Integer> x : elements)  for (int ele : x) arr.add(ele);
        Collections.sort(arr);
        Collections.reverse(arr);
        long ans = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (k == 0) break;
            ans += arr.get(i);
            k--;
        }
        return ans;
    }
    private long solve(int ind, int k, ArrayList<Integer> arr) {
        if (ind >= arr.size()) return 0;
        if (dp[ind][k] != -1) return dp[ind][k];
        long op1 = Integer.MIN_VALUE / 10, op2 = Integer.MIN_VALUE / 10;
        if (k > 0) op1 = arr.get(ind) + solve(ind + 1, k - 1, arr);
        op2 = solve(ind + 1, k, arr);
        return dp[ind][k] = Math.max(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

