# 3383. Taking Maximum Energy From The Mystic Dungeon

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3383-taking-maximum-energy-from-the-mystic-dungeon){ .md-button }

---

<h2><a href="https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon">3383. Taking Maximum Energy From the Mystic Dungeon</a></h2><h3>Medium</h3><hr><p>In a mystic dungeon, <code>n</code> magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.</p>

<p>You have been cursed in such a way that after absorbing energy from magician <code>i</code>, you will be instantly transported to magician <code>(i + k)</code>. This process will be repeated until you reach the magician where <code>(i + k)</code> does not exist.</p>

<p>In other words, you will choose a starting point and then teleport with <code>k</code> jumps until you reach the end of the magicians&#39; sequence, <strong>absorbing all the energy</strong> during the journey.</p>

<p>You are given an array <code>energy</code> and an integer <code>k</code>. Return the <strong>maximum</strong> possible energy you can gain.</p>

<p><strong>Note</strong> that when you are reach a magician, you <em>must</em> take energy from them, whether it is negative or positive energy.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [5,2,-10,-5,1], k = 3</span></p>

<p><strong>Output:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> 3</span></p>

<p><strong>Explanation:</strong> We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [-2,-3,-1], k = 2</span></p>

<p><strong>Output:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> -1</span></p>

<p><strong>Explanation:</strong> We can gain a total energy of -1 by starting from magician 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= energy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-1000 &lt;= energy[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= energy.length - 1</code></li>
</ul>

<p>&nbsp;</p>
​​​​​​

---

## Solution

```java
class Solution {
    private int dp[][];
    public int maximumEnergy(int[] arr, int k) {
        int n = arr.length; 
        dp = new int[n + 1][2];
        for (int current[] : dp)
            Arrays.fill(current, (int)(-1e9));
        return solve(0, arr, 0, k);
    }
    private int solve(int idx, int arr[], int taken, int k) {
        if (idx >= arr.length)  {
            if (taken == 0) {
                return Integer.MIN_VALUE;
            }
            return 0;
        }
        if (dp[idx][taken] != (int)(-1e9))
            return dp[idx][taken];
        if (taken == 0) {
            int op1 = arr[idx] + solve(idx + k, arr, 1, k);
            int op2 = solve(idx + 1, arr, 0, k);
            return dp[idx][taken] = Math.max(op1, op2);
        } else {
            return dp[idx][taken] = arr[idx] + solve(idx + k, arr, 1, k);
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

