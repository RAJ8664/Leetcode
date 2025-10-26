# 1352. Maximum Profit In Job Scheduling

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-profit-in-job-scheduling/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1352-maximum-profit-in-job-scheduling){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-profit-in-job-scheduling">1352. Maximum Profit in Job Scheduling</a></h2><h3>Hard</h3><hr><p>We have <code>n</code> jobs, where every job is scheduled to be done from <code>startTime[i]</code> to <code>endTime[i]</code>, obtaining a profit of <code>profit[i]</code>.</p>

<p>You&#39;re given the <code>startTime</code>, <code>endTime</code> and <code>profit</code> arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.</p>

<p>If you choose a job that ends at time <code>X</code> you will be able to start another job that starts at time <code>X</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/10/10/sample1_1584.png" style="width: 380px; height: 154px;" /></strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
<strong>Output:</strong> 120
<strong>Explanation:</strong> The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/10/10/sample22_1584.png" style="width: 600px; height: 112px;" /> </strong></p>

<pre>
<strong>Input:</strong> startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
<strong>Output:</strong> 150
<strong>Explanation:</strong> The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/10/10/sample3_1584.png" style="width: 400px; height: 112px;" /></strong></p>

<pre>
<strong>Input:</strong> startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startTime.length == endTime.length == profit.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= startTime[i] &lt; endTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= profit[i] &lt;= 10<sup>4</sup></code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    static class Tuple {
        int start, end, profit;
        public Tuple(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
        @Override
        public String toString() {
            return "Tuple{" +
                   "start=" + start +
                   ", end=" + end +
                   ", profit=" + profit +
                   '}';
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(first.start, second.start);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.end, second.end);
        }
    }
    private ArrayList<Tuple> arr;
    private int dp[];
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            arr.add(new Tuple(startTime[i], endTime[i], profit[i]));
        Collections.sort(arr, new customSort());
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(0);
    }
    private int solve(int ind) {
        if (ind >= arr.size())
            return 0;
        if (dp[ind] != -1)
            return dp[ind];

        int low = ind + 1, high = arr.size() - 1, next = arr.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid).start >= arr.get(ind).end) {
                next = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }

        int op1 = solve(ind + 1);
        int op2 = arr.get(ind).profit + solve(next);

        return dp[ind] = Math.max(op1, op2);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

