# 3656. Minimum Number Of Operations To Make Elements In Array Distinct

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3656-minimum-number-of-operations-to-make-elements-in-array-distinct){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct">3656. Minimum Number of Operations to Make Elements in Array Distinct</a></h2><h3>Easy</h3><hr><p>You are given an integer array <code>nums</code>. You need to ensure that the elements in the array are <strong>distinct</strong>. To achieve this, you can perform the following operation any number of times:</p>

<ul>
	<li>Remove 3 elements from the beginning of the array. If the array has fewer than 3 elements, remove all remaining elements.</li>
</ul>

<p><strong>Note</strong> that an empty array is considered to have distinct elements. Return the <strong>minimum</strong> number of operations needed to make the elements in the array distinct.<!-- notionvc: 210ee4f2-90af-4cdf-8dbc-96d1fa8f67c7 --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,2,3,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first operation, the first 3 elements are removed, resulting in the array <code>[4, 2, 3, 3, 5, 7]</code>.</li>
	<li>In the second operation, the next 3 elements are removed, resulting in the array <code>[3, 5, 7]</code>, which has distinct elements.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,6,4,4]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first operation, the first 3 elements are removed, resulting in the array <code>[4, 4]</code>.</li>
	<li>In the second operation, all remaining elements are removed, resulting in an empty array.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array already contains distinct elements. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int freq[];
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int ele : nums) dq.addLast(ele);
        freq = new int[101];
        for (int ele : nums) freq[ele]++;
        int count = 0;
        while (true) {
            if (check() == true) break;
            if (dq.size() > 0) freq[dq.pollFirst()]--;
            if (dq.size() > 0) freq[dq.pollFirst()]--;
            if (dq.size() > 0) freq[dq.pollFirst()]--;
            count++;
        }
        return count;
    }
    private boolean check() {
        for (int i = 0; i <= 100; i++) {
            if (freq[i] > 1) return false;
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

