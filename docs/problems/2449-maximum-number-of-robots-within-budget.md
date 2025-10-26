# 2449. Maximum Number Of Robots Within Budget

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-robots-within-budget/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2449-maximum-number-of-robots-within-budget){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-robots-within-budget">2449. Maximum Number of Robots Within Budget</a></h2><h3>Hard</h3><hr><p>You have <code>n</code> robots. You are given two <strong>0-indexed</strong> integer arrays, <code>chargeTimes</code> and <code>runningCosts</code>, both of length <code>n</code>. The <code>i<sup>th</sup></code> robot costs <code>chargeTimes[i]</code> units to charge and costs <code>runningCosts[i]</code> units to run. You are also given an integer <code>budget</code>.</p>

<p>The <strong>total cost</strong> of running <code>k</code> chosen robots is equal to <code>max(chargeTimes) + k * sum(runningCosts)</code>, where <code>max(chargeTimes)</code> is the largest charge cost among the <code>k</code> robots and <code>sum(runningCosts)</code> is the sum of running costs among the <code>k</code> robots.</p>

<p>Return<em> the <strong>maximum</strong> number of <strong>consecutive</strong> robots you can run such that the total cost <strong>does not</strong> exceed </em><code>budget</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
It is possible to run all individual and consecutive pairs of robots within budget.
To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
<strong>Output:</strong> 0
<strong>Explanation:</strong> No robot can be run that does not exceed the budget, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>chargeTimes.length == runningCosts.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= chargeTimes[i], runningCosts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>15</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        int low = 1, high = n, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, chargeTimes, runningCosts, budget)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    private boolean ok(int mid, int chargeTimes[], int runningCosts[], long budget) {
        int n = chargeTimes.length, start = 0;
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        long current_sum = 0;
        for (int i = 0; i < mid; i++) {
            int current = chargeTimes[i];
            set.add(current);
            map.put(current, map.getOrDefault(current, 0) + 1);
            current_sum += runningCosts[i];
        }
        long current_budget = set.last() * 1L +  mid * 1L * current_sum;
        if (current_budget <= budget) return true;
        for (int i = mid; i < n; i++) {
            int current = chargeTimes[i];
            current_sum += runningCosts[i]; current_sum -= runningCosts[start];
            map.put(current, map.getOrDefault(current, 0) + 1);
            map.put(chargeTimes[start], map.getOrDefault(chargeTimes[start], 0) -1);
            set.add(current);
            if (map.getOrDefault(chargeTimes[start], 0) == 0) {
                map.remove(chargeTimes[start]);
                set.remove(chargeTimes[start]);
            }
            start++;
            current_budget = set.last() * 1L + mid * (current_sum);
            if (current_budget <= budget) return true;
        }
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

