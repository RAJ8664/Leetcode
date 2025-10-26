# 3241. Divide Array Into Arrays With Max Difference

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3241-divide-array-into-arrays-with-max-difference){ .md-button }

---

<h2><a href="https://leetcode.com/problems/divide-array-into-arrays-with-max-difference">3241. Divide Array Into Arrays With Max Difference</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code> of size <code>n</code> where <code>n</code> is a multiple of 3 and a positive integer <code>k</code>.</p>

<p>Divide the array <code>nums</code> into <code>n / 3</code> arrays of size <strong>3</strong> satisfying the following condition:</p>

<ul>
	<li>The difference between <strong>any</strong> two elements in one array is <strong>less than or equal</strong> to <code>k</code>.</li>
</ul>

<p>Return a <strong>2D</strong> array containing the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,4,8,7,9,3,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,1,3],[3,4,5],[7,8,9]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The difference between any two elements in each array is less than or equal to 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,2,2,5,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>Different ways to divide <code>nums</code> into 2 arrays of size 3 are:</p>

<ul>
	<li>[[2,2,2],[2,4,5]] (and its permutations)</li>
	<li>[[2,2,4],[2,2,5]] (and its permutations)</li>
</ul>

<p>Because there are four 2s there will be an array with the elements 2 and 5 no matter how we divide it. since <code>5 - 2 = 3 &gt; k</code>, the condition is not satisfied and so there is no valid division.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The difference between any two elements in each array is less than or equal to 14.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n </code>is a multiple of 3</li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        int ans[][] = new int[n / 3][3];
        if (n % 3 != 0)
            return ans;
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int i = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        while(i < n) {
            if(temp.size() == 3) {
                res.add(new ArrayList<>(temp));
                temp.clear();
            }
            else 
                temp.add(nums[i++]);
        }
        if(temp.size() == 3) 
            res.add(new ArrayList<>(temp));        
        for(int l = 0; l < res.size(); l++) {
            for(int j = 0; j < res.get(l).size(); j++) 
                ans[l][j] = res.get(l).get(j);
        }
        boolean found = false;
        for(ArrayList<Integer> current : res) {
            int first = current.get(0), second = current.get(1), third = current.get(2);
            if(Math.abs(first - second) <= k && Math.abs(second - third) <= k && Math.abs(first - third) <= k) 
                continue;
            else 
                found = true;
            if (found == true)
                break;
        }
        if(found) 
            return new int[][]{};
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

