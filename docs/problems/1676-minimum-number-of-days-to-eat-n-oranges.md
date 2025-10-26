# 1676. Minimum Number Of Days To Eat N Oranges

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1676-minimum-number-of-days-to-eat-n-oranges){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges">1676. Minimum Number of Days to Eat N Oranges</a></h2><h3>Hard</h3><hr><p>There are <code>n</code> oranges in the kitchen and you decided to eat some of these oranges every day as follows:</p>

<ul>
	<li>Eat one orange.</li>
	<li>If the number of remaining oranges <code>n</code> is divisible by <code>2</code> then you can eat <code>n / 2</code> oranges.</li>
	<li>If the number of remaining oranges <code>n</code> is divisible by <code>3</code> then you can eat <code>2 * (n / 3)</code> oranges.</li>
</ul>

<p>You can only choose one of the actions per day.</p>

<p>Given the integer <code>n</code>, return <em>the minimum number of days to eat</em> <code>n</code> <em>oranges</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> You have 10 oranges.
Day 1: Eat 1 orange,  10 - 1 = 9.  
Day 2: Eat 6 oranges, 9 - 2*(9/3) = 9 - 6 = 3. (Since 9 is divisible by 3)
Day 3: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. 
Day 4: Eat the last orange  1 - 1  = 0.
You need at least 4 days to eat the 10 oranges.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> You have 6 oranges.
Day 1: Eat 3 oranges, 6 - 6/2 = 6 - 3 = 3. (Since 6 is divisible by 2).
Day 2: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. (Since 3 is divisible by 3)
Day 3: Eat the last orange  1 - 1  = 0.
You need at least 3 days to eat the 6 oranges.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
import java.util.HashMap;

class Solution {
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public int minDays(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (memo.containsKey(n))
            return memo.get(n);

        int op1 = (n % 2) + 1 + minDays(n / 2);
        int op2 = (n % 3) + 1 + minDays(n / 3);
        int result = Math.min(op1, op2);
        memo.put(n, result);

        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

