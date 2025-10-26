# 1422. Divide Array In Sets Of K Consecutive Numbers

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1422-divide-array-in-sets-of-k-consecutive-numbers){ .md-button }

---

<h2><a href="https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers">1422. Divide Array in Sets of K Consecutive Numbers</a></h2><h3>Medium</h3><hr><p>Given an array of integers <code>nums</code> and a positive integer <code>k</code>, check whether it is possible to divide this array into sets of <code>k</code> consecutive numbers.</p>

<p>Return <code>true</code> <em>if it is possible</em>.<strong> </strong>Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,4,4,5,6], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3,4] and [3,4,5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> Each array should be divided in subarrays of size 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Note:</strong> This question is the same as&nbsp;846:&nbsp;<a href="https://leetcode.com/problems/hand-of-straights/" target="_blank">https://leetcode.com/problems/hand-of-straights/</a>

---

## Solution

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int ele : nums) {
            set.add(ele);
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        while (map.size() > 0) {
            int current = set.first();
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(current))
                    return false;
                else {
                    map.put(current, map.getOrDefault(current, 0) -1);
                    if (map.getOrDefault(current, 0) == 0) {
                        map.remove(current);
                        set.remove(current);
                    }
                }
                current++;
            }
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

