# 2839. Maximum Sum Queries

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-sum-queries/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2839-maximum-sum-queries){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-sum-queries">2839. Maximum Sum Queries</a></h2><h3>Hard</h3><hr><p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, each of length <code>n</code>, and a <strong>1-indexed 2D array</strong> <code>queries</code> where <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>For the <code>i<sup>th</sup></code> query, find the <strong>maximum value</strong> of <code>nums1[j] + nums2[j]</code> among all indices <code>j</code> <code>(0 &lt;= j &lt; n)</code>, where <code>nums1[j] &gt;= x<sub>i</sub></code> and <code>nums2[j] &gt;= y<sub>i</sub></code>, or <strong>-1</strong> if there is no <code>j</code> satisfying the constraints.</p>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
<strong>Output:</strong> [6,10,7]
<strong>Explanation:</strong> 
For the 1st query <code node="[object Object]">x<sub>i</sub> = 4</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 1</code>, we can select index&nbsp;<code node="[object Object]">j = 0</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 4</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 1</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 6, and we can show that 6 is the maximum we can obtain.

For the 2nd query <code node="[object Object]">x<sub>i</sub> = 1</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 3</code>, we can select index&nbsp;<code node="[object Object]">j = 2</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 1</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 3</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 10, and we can show that 10 is the maximum we can obtain. 

For the 3rd query <code node="[object Object]">x<sub>i</sub> = 2</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 5</code>, we can select index&nbsp;<code node="[object Object]">j = 3</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 2</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 5</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 7, and we can show that 7 is the maximum we can obtain.

Therefore, we return&nbsp;<code node="[object Object]">[6,10,7]</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
<strong>Output:</strong> [9,9,9]
<strong>Explanation:</strong> For this example, we can use index&nbsp;<code node="[object Object]">j = 2</code>&nbsp;for all the queries since it satisfies the constraints for each query.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> There is one query in this example with <code node="[object Object]">x<sub>i</sub></code> = 3 and <code node="[object Object]">y<sub>i</sub></code> = 3. For every index, j, either nums1[j] &lt; <code node="[object Object]">x<sub>i</sub></code> or nums2[j] &lt; <code node="[object Object]">y<sub>i</sub></code>. Hence, there is no solution. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == nums2.length</code>&nbsp;</li>
	<li><code>n ==&nbsp;nums1.length&nbsp;</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup>&nbsp;</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length ==&nbsp;2</code></li>
	<li><code>x<sub>i</sub>&nbsp;== queries[i][1]</code></li>
	<li><code>y<sub>i</sub> == queries[i][2]</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int q2[][] = new int[queries.length][3], ans[] = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            q2[i][0] = queries[i][0];
            q2[i][1] = queries[i][1];
            q2[i][2] = i;
        }
        int nums[][] = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; ++i) {
            nums[i][0] = nums1[i];
            nums[i][1] = nums2[i];
        }
        Arrays.sort(nums, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(q2, (a, b) -> Integer.compare(a[0], b[0]));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int index = nums.length - 1;
        for (int i = q2.length - 1; i >= 0; --i) {
            while (index >= 0 && nums[index][0] >= q2[i][0]) {
                insert(tm, nums[index][0], nums[index][1]);
                index--;
            }
            var entry = tm.ceilingEntry(q2[i][1]);
            if (entry == null) ans[q2[i][2]] = -1;
            else ans[q2[i][2]] = entry.getValue();
        }
        return ans;
    }

    private void insert(TreeMap<Integer, Integer> tm, int x, int y) {
        int sum = x + y;
        if (tm.containsKey(y) && tm.get(y) >= sum) return;
        Integer higher = tm.higherKey(y);
        if (higher != null && tm.get(higher) >= sum) return;
        Set<Integer> hs = new HashSet<>();
        Integer lower = tm.lowerKey(y);
        while (lower != null) {
            if (tm.get(lower) <= sum) hs.add(lower);
            else break;
            lower = tm.lowerKey(lower);
        }
        for (int i : hs) tm.remove(i);
        tm.put(y, sum);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

