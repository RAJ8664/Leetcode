# 2661. Smallest Missing Non Negative Integer After Operations

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2661-smallest-missing-non-negative-integer-after-operations){ .md-button }

---

<h2><a href="https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations">2661. Smallest Missing Non-negative Integer After Operations</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>value</code>.</p>

<p>In one operation, you can add or subtract <code>value</code> from any element of <code>nums</code>.</p>

<ul>
	<li>For example, if <code>nums = [1,2,3]</code> and <code>value = 2</code>, you can choose to subtract <code>value</code> from <code>nums[0]</code> to make <code>nums = [-1,2,3]</code>.</li>
</ul>

<p>The MEX (minimum excluded) of an array is the smallest missing <strong>non-negative</strong> integer in it.</p>

<ul>
	<li>For example, the MEX of <code>[-1,2,3]</code> is <code>0</code> while the MEX of <code>[1,0,3]</code> is <code>2</code>.</li>
</ul>

<p>Return <em>the maximum MEX of </em><code>nums</code><em> after applying the mentioned operation <strong>any number of times</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> One can achieve this result by applying the following operations:
- Add value to nums[1] twice to make nums = [1,<strong><u>0</u></strong>,7,13,6,8]
- Subtract value from nums[2] once to make nums = [1,0,<strong><u>2</u></strong>,13,6,8]
- Subtract value from nums[3] twice to make nums = [1,0,2,<strong><u>3</u></strong>,6,8]
The MEX of nums is 4. It can be shown that 4 is the maximum MEX we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 7
<strong>Output:</strong> 2
<strong>Explanation:</strong> One can achieve this result by applying the following operation:
- subtract value from nums[2] once to make nums = [1,-10,<u><strong>0</strong></u>,13,6,8]
The MEX of nums is 2. It can be shown that 2 is the maximum MEX we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, value &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            int curr = ((ele % value) + value) % value;
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        int res = 0;
        while (map.getOrDefault(res % value, 0) > 0) {
            map.put(res % value, map.getOrDefault(res % value, 0) - 1);
            res++;
        } 
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

