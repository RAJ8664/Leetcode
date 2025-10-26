# 673. Number Of Longest Increasing Subsequence


---

<h2><a href="https://leetcode.com/problems/number-of-longest-increasing-subsequence">673. Number of Longest Increasing Subsequence</a></h2><h3>Medium</h3><hr><p>Given an integer array&nbsp;<code>nums</code>, return <em>the number of longest increasing subsequences.</em></p>

<p><strong>Notice</strong> that the sequence has to be <strong>strictly</strong> increasing.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,4,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>The answer is guaranteed to fit inside a 32-bit integer.</li>
</ul>


## Solution

```java
import java.util.Arrays;

class Solution {
    static class Pair {
        int maxLen, count;
        public Pair(int maxLen, int count) {
            this.maxLen = maxLen;
            this.count = count;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "maxLen=" + maxLen +
                   ", count=" + count +
                   '}';
        }
    }
    private Pair[][] dp;
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        dp = new Pair[n + 1][n + 1];
        for (Pair current[] : dp)
            Arrays.fill(current, null);
        return solve(0, -1, nums).count;
    }

    private Pair solve(int ind, int prev, int arr[]) {
        if (ind >= arr.length)
            return new Pair(0, 1);

        if (dp[ind][prev + 1] != null)
            return dp[ind][prev + 1];

        Pair op1 = solve(ind + 1, prev, arr);

        int op2maxLen = 0, op2count = 0;
        if (prev == -1 || arr[ind] > arr[prev]) {
            Pair op2 = solve(ind + 1, ind, arr);
            op2maxLen = 1 + op2.maxLen;
            op2count = op2.count;
        }

        Pair ans = new Pair(0, 0);
        if (op1.maxLen > op2maxLen) {
            ans.maxLen = op1.maxLen;
            ans.count = op1.count;
        } else if (op2maxLen > op1.maxLen) {
            ans.maxLen = op2maxLen;
            ans.count = op2count;
        } else if (op1.maxLen == op2maxLen) {
            ans.maxLen = op1.maxLen;
            ans.count = op1.count + op2count;
        }
        return dp[ind][prev + 1] = ans;
    }

}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

