# 611. Valid Triangle Number

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/valid-triangle-number/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0611-valid-triangle-number){ .md-button }

---

<h2><a href="https://leetcode.com/problems/valid-triangle-number">611. Valid Triangle Number</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code>, return <em>the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3,4]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int low = j + 1, high = n - 1, ansLeft = -1, ansRight = -1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[i] + nums[mid] > nums[j]) {
                        ansLeft = mid;
                        high = mid - 1;
                    }
                    else 
                        low = mid + 1;
                }
                low = j + 1; high = n - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[i] + nums[j] > nums[mid]) {
                        ansRight = mid;
                        low = mid + 1;
                    }
                    else high = mid - 1;
                }
                if (ansLeft == -1 || ansRight == -1) continue; 
                if (ansLeft <= ansRight) {
                    count += (ansRight - ansLeft + 1);
                }
            } 
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

