# 892. Shortest Subarray With Sum At Least K

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0892-shortest-subarray-with-sum-at-least-k){ .md-button }

---

<h2><a href="https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k">892. Shortest Subarray with Sum at Least K</a></h2><h3>Hard</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the length of the shortest non-empty <strong>subarray</strong> of </em><code>nums</code><em> with a sum of at least </em><code>k</code>. If there is no such <strong>subarray</strong>, return <code>-1</code>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1], k = 1
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,2], k = 4
<strong>Output:</strong> -1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [2,-1,2], k = 3
<strong>Output:</strong> 3
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        long key, value;
        public Pair(long key, long value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "(" + key + " " + value + ")";
        }
    }
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        long current_sum = 0;
        Deque<Pair> q = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            current_sum += nums[r];
            if (current_sum >= k) res = Math.min(res, r + 1);
            while (!q.isEmpty() && current_sum - q.peekFirst().key >= k) {
                Pair front = q.pollFirst();
                res = (int)(Math.min(res, r - front.value));
            }
            while (!q.isEmpty() && q.peekLast().key > current_sum) q.pollLast();
            q.offerLast(new Pair(current_sum, r));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

