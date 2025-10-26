# 2893. Visit Array Positions To Maximize Score

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/visit-array-positions-to-maximize-score/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2893-visit-array-positions-to-maximize-score){ .md-button }

---

<h2><a href="https://leetcode.com/problems/visit-array-positions-to-maximize-score">2893. Visit Array Positions to Maximize Score</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a positive integer <code>x</code>.</p>

<p>You are <strong>initially</strong> at position <code>0</code> in the array and you can visit other positions according to the following rules:</p>

<ul>
	<li>If you are currently in position <code>i</code>, then you can move to <strong>any</strong> position <code>j</code> such that <code>i &lt; j</code>.</li>
	<li>For each position <code>i</code> that you visit, you get a score of <code>nums[i]</code>.</li>
	<li>If you move from a position <code>i</code> to a position <code>j</code> and the <strong>parities</strong> of <code>nums[i]</code> and <code>nums[j]</code> differ, then you lose a score of <code>x</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> total score you can get</em>.</p>

<p><strong>Note</strong> that initially you have <code>nums[0]</code> points.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,6,1,9,2], x = 5
<strong>Output:</strong> 13
<strong>Explanation:</strong> We can visit the following positions in the array: 0 -&gt; 2 -&gt; 3 -&gt; 4.
The corresponding values are 2, 6, 1 and 9. Since the integers 6 and 1 have different parities, the move 2 -&gt; 3 will make you lose a score of x = 5.
The total score will be: 2 + 6 + 1 + 9 - 5 = 13.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6,8], x = 3
<strong>Output:</strong> 20
<strong>Explanation:</strong> All the integers in the array have the same parities, so we can visit all of them without losing any score.
The total score is: 2 + 4 + 6 + 8 = 20.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private long dp[][];
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        dp = new long[n][2];
        for (long current[] : dp)
            Arrays.fill(current, -1);
        return nums[0] + solve(1, nums[0] % 2, nums, x);
    }
    private long solve(int ind, int parity, int arr[], int x) {
        if (ind >= arr.length)
            return 0;
        if (dp[ind][parity] != -1)
            return dp[ind][parity];

        long op1 = 0, op2 = 0;
        op1 = solve(ind + 1, parity, arr, x);
        if (arr[ind] % 2 == parity)
            op2 = arr[ind] + solve(ind + 1, parity, arr, x);
        else
            op2 = arr[ind] - x + solve(ind + 1, 1 - parity, arr, x);

        return dp[ind][parity] = Math.max(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

