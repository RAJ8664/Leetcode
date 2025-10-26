# 3437. Maximum Total Damage With Spell Casting

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-total-damage-with-spell-casting/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3437-maximum-total-damage-with-spell-casting){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-total-damage-with-spell-casting">3437. Maximum Total Damage With Spell Casting</a></h2><h3>Medium</h3><hr><p>A magician has various spells.</p>

<p>You are given an array <code>power</code>, where each element represents the damage of a spell. Multiple spells can have the same damage value.</p>

<p>It is a known fact that if a magician decides to cast a spell with a damage of <code>power[i]</code>, they <strong>cannot</strong> cast any spell with a damage of <code>power[i] - 2</code>, <code>power[i] - 1</code>, <code>power[i] + 1</code>, or <code>power[i] + 2</code>.</p>

<p>Each spell can be cast <strong>only once</strong>.</p>

<p>Return the <strong>maximum</strong> possible <em>total damage</em> that a magician can cast.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">power = [1,1,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">power = [7,1,6,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private long dp[];
    private HashMap<Integer, Integer> freq;
    public long maximumTotalDamage(int[] arr) {
        int n = arr.length;
        dp = new long[n + 1];
        Arrays.fill(dp, -1L);
        freq = new HashMap<>();
        for (int ele : arr) 
            freq.put(ele, freq.getOrDefault(ele, 0) + 1);
        ArrayList<Integer> nums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> curr : freq.entrySet()) 
            nums.add(curr.getKey());
        Collections.sort(nums);
        return solve(0, nums); 
    }
    private long solve(int ind, ArrayList<Integer> nums) {
        if (ind >= nums.size()) 
            return 0L;
        if (dp[ind] != -1) 
            return dp[ind] * 1L;
        long op1 = solve(ind + 1, nums);
        int nextIdx = bs(ind + 1, nums.get(ind), nums);
        long op2 = nums.get(ind)  * 1L *  freq.get(nums.get(ind)) + solve(nextIdx, nums);
        return dp[ind] = Math.max(op1, op2);
    }
    private int bs(int start, int target, ArrayList<Integer> arr) {
        int n = arr.size();
        int low = start, high = arr.size() - 1, ans = arr.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) > target + 2) {
                ans = mid;
                high = mid - 1;
            } else 
                low = mid + 1;
        } 
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

