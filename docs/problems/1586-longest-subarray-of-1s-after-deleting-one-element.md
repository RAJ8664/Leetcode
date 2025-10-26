# 1586. Longest Subarray Of 1S After Deleting One Element

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1586-longest-subarray-of-1s-after-deleting-one-element){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element">1586. Longest Subarray of 1's After Deleting One Element</a></h2><h3>Medium</h3><hr><p>Given a binary array <code>nums</code>, you should delete one element from it.</p>

<p>Return <em>the size of the longest non-empty subarray containing only </em><code>1</code><em>&#39;s in the resulting array</em>. Return <code>0</code> if there is no such subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,1,0,1,1,0,1]
<strong>Output:</strong> 5
<strong>Explanation:</strong> After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1&#39;s is [1,1,1,1,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You must delete one element.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int start = 0, ans = 0, zeroes = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zeroes++;
            while (zeroes > 1) {
                if (nums[start] == 0) zeroes--;
                start++;
            }
            ans = Math.max(ans, i - start + 1 - zeroes);
        }
        return ans == n ? ans - 1 : ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

