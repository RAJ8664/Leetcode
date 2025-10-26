# 826. Soup Servings

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/soup-servings/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0826-soup-servings){ .md-button }

---

<h2><a href="https://leetcode.com/problems/soup-servings">826. Soup Servings</a></h2><h3>Medium</h3><hr><p>You have two soups, <strong>A</strong> and <strong>B</strong>, each starting with <code>n</code> mL. On every turn, one of the following four serving operations is chosen <em>at random</em>, each with probability <code>0.25</code> <strong>independent</strong> of all previous turns:</p>

<ul>
	<li>pour 100 mL from type A and 0 mL from type B</li>
	<li>pour 75 mL from type A and 25 mL from type B</li>
	<li>pour 50 mL from type A and 50 mL from type B</li>
	<li>pour 25 mL from type A and 75 mL from type B</li>
</ul>

<p><strong>Note:</strong></p>

<ul>
	<li>There is no operation that pours 0 mL from A and 100 mL from B.</li>
	<li>The amounts from A and B are poured <em>simultaneously</em> during the turn.</li>
	<li>If an operation asks you to pour <strong>more than</strong> you have left of a soup, pour all that remains of that soup.</li>
</ul>

<p>The process stops immediately after any turn in which <em>one of the soups</em> is used up.</p>

<p>Return the probability that A is used up <em>before</em> B, plus half the probability that both soups are used up in the<strong> same turn</strong>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 0.62500
<strong>Explanation:</strong> 
If we perform either of the first two serving operations, soup A will become empty first.
If we perform the third operation, A and B will become empty at the same time.
If we perform the fourth operation, B will become empty first.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 0.71875
<strong>Explanation:</strong> 
If we perform the first serving operation, soup A will become empty first.
If we perform the second serving operations, A will become empty on performing operation [1, 2, 3], and both A and B become empty on performing operation 4.
If we perform the third operation, A will become empty on performing operation [1, 2], and both A and B become empty on performing operation 3.
If we perform the fourth operation, A will become empty on performing operation 1, and both A and B become empty on performing operation 2.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.71875.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private Map<Pair, Double> mp = new HashMap<>();

    private double recur(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0 && b > 0) return 1;
        if (a > 0 && b <= 0) return 0;
        if (mp.containsKey(new Pair(a, b))) return mp.get(new Pair(a, b));

        double op1 = recur(a - 100, b);
        double op2 = recur(a - 75, b - 25);
        double op3 = recur(a - 50, b - 50);
        double op4 = recur(a - 25, b - 75);

        double result = 0.25 * (op1 + op2 + op3 + op4);
        mp.put(new Pair(a, b), result);
        return result;
    }

    public double soupServings(int n) {
        if (n >= 4800) return 1;
        double ans = recur(n, n);
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

