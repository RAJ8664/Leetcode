# 1042. Minimum Cost To Merge Stones

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-cost-to-merge-stones/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1042-minimum-cost-to-merge-stones){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-cost-to-merge-stones">1042. Minimum Cost to Merge Stones</a></h2><h3>Hard</h3><hr><p>There are <code>n</code> piles of <code>stones</code> arranged in a row. The <code>i<sup>th</sup></code> pile has <code>stones[i]</code> stones.</p>

<p>A move consists of merging exactly <code>k</code> <strong>consecutive</strong> piles into one pile, and the cost of this move is equal to the total number of stones in these <code>k</code> piles.</p>

<p>Return <em>the minimum cost to merge all piles of stones into one pile</em>. If it is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [3,2,4,1], k = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [3,2,4,1], k = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> After any merge operation, there are 2 piles left, and we can&#39;t merge anymore.  So the task is impossible.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [3,5,1,2,6], k = 3
<strong>Output:</strong> 25
<strong>Explanation:</strong> We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stones.length</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 30</code></li>
</ul>


---

## Solution

```java
import java.util.*;
class Solution {
    private int[][][] memo;
    private int[] prefix;
    private int k;
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        this.k = k;
        
        if ((n - 1) % (k - 1) != 0) return -1;

        prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        memo = new int[n][n][k + 1];
        for (int[][] layer : memo)
            for (int[] row : layer)
                Arrays.fill(row, -1);
        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int piles) {
        if (memo[i][j][piles] != -1) return memo[i][j][piles];

        if (j - i + 1 < piles) return Integer.MAX_VALUE;

        if (i == j) {
            return piles == 1 ? 0 : Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        if (piles > 1) {
            for (int m = i; m < j; m += (k - 1)) {
                int left = dfs(i, m, 1);
                int right = dfs(m + 1, j, piles - 1);
                if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE) continue;
                res = Math.min(res, left + right);
            }
        } else {
            int temp = dfs(i, j, k);
            if (temp != Integer.MAX_VALUE)
                res = temp + prefix[j + 1] - prefix[i];
        }
        return memo[i][j][piles] = res;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

