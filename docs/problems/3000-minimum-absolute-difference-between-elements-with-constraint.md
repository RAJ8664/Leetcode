# 3000. Minimum Absolute Difference Between Elements With Constraint

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3000-minimum-absolute-difference-between-elements-with-constraint){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint">3000. Minimum Absolute Difference Between Elements With Constraint</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>x</code>.</p>

<p>Find the <strong>minimum absolute difference</strong> between two elements in the array that are at least <code>x</code> indices apart.</p>

<p>In other words, find two indices <code>i</code> and <code>j</code> such that <code>abs(i - j) &gt;= x</code> and <code>abs(nums[i] - nums[j])</code> is minimized.</p>

<p>Return<em> an integer denoting the <strong>minimum</strong> absolute difference between two elements that are at least</em> <code>x</code> <em>indices apart</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,4], x = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> We can select nums[0] = 4 and nums[3] = 4. 
They are at least 2 indices apart, and their absolute difference is the minimum, 0. 
It can be shown that 0 is the optimal answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,3,2,10,15], x = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can select nums[1] = 3 and nums[2] = 2.
They are at least 1 index apart, and their absolute difference is the minimum, 1.
It can be shown that 1 is the optimal answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], x = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can select nums[0] = 1 and nums[3] = 4.
They are at least 3 indices apart, and their absolute difference is the minimum, 3.
It can be shown that 3 is the optimal answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= x &lt; nums.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node, ind;
        public Pair(int node, int ind) {
            this.node = node;
            this.ind = ind;
        }
        @Override
        public String toString() {
            return "(" + node + " " + ind + ")";
        }
    }
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        int mini = Integer.MAX_VALUE;
        if (x == 0) return 0;
        Queue<Pair> q = new LinkedList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = nums.get(i);
            if (q.size() > 0 && Math.abs(q.peek().ind - i) >= x) set.add(q.poll().node);
            Integer ceil = set.ceiling(current);
            Integer floor = set.floor(current);
            if (ceil != null) mini = Math.min(mini, Math.abs(current - ceil));
            if (floor != null) mini = Math.min(mini, Math.abs(current - floor));
            q.offer(new Pair(current, i));
        }
        return mini;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

