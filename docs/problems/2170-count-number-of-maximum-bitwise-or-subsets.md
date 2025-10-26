# 2170. Count Number Of Maximum Bitwise Or Subsets

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2170-count-number-of-maximum-bitwise-or-subsets){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets">2170. Count Number of Maximum Bitwise-OR Subsets</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code>, find the <strong>maximum</strong> possible <strong>bitwise OR</strong> of a subset of <code>nums</code> and return <em>the <strong>number of different non-empty subsets</strong> with the maximum bitwise OR</em>.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>. Two subsets are considered <strong>different</strong> if the indices of the elements chosen are different.</p>

<p>The bitwise OR of an array <code>a</code> is equal to <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code> (<strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 2<sup>3</sup> - 1 = 7 total subsets.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Integer>> res;
    public int countMaxOrSubsets(int[] nums) {
        res = new ArrayList<>();
        solve(0, nums, new ArrayList<>());
        int maxi = 0;
        for(ArrayList<Integer> current : res) {
            int res2 = 0;
            for(int ele : current) {
                res2 |= ele;
            }
            maxi = Math.max(maxi, res2);
        }
        int count = 0;
        for(ArrayList<Integer> current : res) {
            int res1 = 0;
            for(int ele : current) {
                res1 |= ele;
            }
            if(res1 == maxi) count++;
        }
        return count;
    }
    private void solve(int ind, int arr[], ArrayList<Integer> temp) {
        if(ind > arr.length - 1) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(arr[ind]);
        solve(ind + 1, arr, temp);
        temp.remove(temp.size() - 1);
        solve(ind + 1, arr, temp);
    }  
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

