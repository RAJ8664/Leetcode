# 3490. Find The Maximum Length Of Valid Subsequence I

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3490-find-the-maximum-length-of-valid-subsequence-i){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i">3490. Find the Maximum Length of Valid Subsequence I</a></h2><h3>Medium</h3><hr>You are given an integer array <code>nums</code>.
<p>A <span data-keyword="subsequence-array">subsequence</span> <code>sub</code> of <code>nums</code> with length <code>x</code> is called <strong>valid</strong> if it satisfies:</p>

<ul>
	<li><code>(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.</code></li>
</ul>

<p>Return the length of the <strong>longest</strong> <strong>valid</strong> subsequence of <code>nums</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 2, 3, 4]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,1,2,1,2]</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 2, 1, 2, 1, 2]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 3]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        first.add(nums[0]); second.add(nums[0]);
        for(int i = 1; i < n; i++) {
            int current = nums[i];
            if((current + first.get(first.size() - 1)) % 2 == 1)   
                first.add(current);
            if((current + second.get(second.size() - 1)) % 2 == 0) 
                second.add(current);
        }
        int res1 = Math.max(first.size(), second.size());
        if(n <= 1)
            return res1;
        first.clear(); second.clear();
        first.add(nums[1]); second.add(nums[1]);
        for(int i = 2; i < n; i++) {
            int current = nums[i];
            if((current + first.get(first.size() - 1)) % 2 == 1) 
                first.add(current);
            if((current + second.get(second.size() - 1)) % 2 == 0) 
                second.add(current);
        }
        int res2 = Math.max(first.size() , second.size());
        int ans = Math.max(res1, res2);
        int even = 0, odd = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] % 2 == 0) 
                even++;
            else if(nums[i] % 2 == 1) 
                odd++;
        }
        ans = Math.max(ans, even);
        ans = Math.max(ans, odd);
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

