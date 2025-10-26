# 2374. Steps To Make Array Non Decreasing

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/steps-to-make-array-non-decreasing/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2374-steps-to-make-array-non-decreasing){ .md-button }

---

<h2><a href="https://leetcode.com/problems/steps-to-make-array-non-decreasing">2374. Steps to Make Array Non-decreasing</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one step, <strong>remove</strong> all elements <code>nums[i]</code> where <code>nums[i - 1] &gt; nums[i]</code> for all <code>0 &lt; i &lt; nums.length</code>.</p>

<p>Return <em>the number of steps performed until </em><code>nums</code><em> becomes a <strong>non-decreasing</strong> array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,3,4,4,7,3,6,11,8,5,11]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following are the steps performed:
- Step 1: [5,<strong><u>3</u></strong>,4,4,7,<u><strong>3</strong></u>,6,11,<u><strong>8</strong></u>,<u><strong>5</strong></u>,11] becomes [5,4,4,7,6,11,11]
- Step 2: [5,<u><strong>4</strong></u>,4,7,<u><strong>6</strong></u>,11,11] becomes [5,4,7,11,11]
- Step 3: [5,<u><strong>4</strong></u>,7,11,11] becomes [5,7,11,11]
[5,7,11,11] is a non-decreasing array. Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,7,7,13]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already a non-decreasing array. Therefore, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int ind, count, node;
        public Pair(int ind, int count, int node) {
            this.ind = ind;
            this.count = count;
            this.node = node;
        }
        @Override
        public String toString() {
            return "(" + ind + " " + count + " " + node + ")";
        }
    }
    public int totalSteps(int[] nums) {
        int n = nums.length;
        int maxi = 0;
        Stack<Pair> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = nums[i];
            int count = 0;
            while (st.size() > 0 && current > st.peek().node) {
                count = Math.max(count + 1, st.peek().count);
                st.pop();
            }
            st.add(new Pair(i, count, current));
            maxi = Math.max(maxi, count);
        }
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

