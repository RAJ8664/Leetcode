# 3750. Closest Equal Element Queries


---

<h2><a href="https://leetcode.com/problems/closest-equal-element-queries">3750. Closest Equal Element Queries</a></h2><h3>Medium</h3><hr><p>You are given a <strong>circular</strong> array <code>nums</code> and an array <code>queries</code>.</p>

<p>For each query <code>i</code>, you have to find the following:</p>

<ul>
	<li>The <strong>minimum</strong> distance between the element at index <code>queries[i]</code> and <strong>any</strong> other index <code>j</code> in the <strong>circular</strong> array, where <code>nums[j] == nums[queries[i]]</code>. If no such index exists, the answer for that query should be -1.</li>
</ul>

<p>Return an array <code>answer</code> of the <strong>same</strong> size as <code>queries</code>, where <code>answer[i]</code> represents the result for query <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,1,4,1,3,2], queries = [0,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,-1,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query 0: The element at <code>queries[0] = 0</code> is <code>nums[0] = 1</code>. The nearest index with the same value is 2, and the distance between them is 2.</li>
	<li>Query 1: The element at <code>queries[1] = 3</code> is <code>nums[3] = 4</code>. No other index contains 4, so the result is -1.</li>
	<li>Query 2: The element at <code>queries[2] = 5</code> is <code>nums[5] = 3</code>. The nearest index with the same value is 1, and the distance between them is 3 (following the circular path: <code>5 -&gt; 6 -&gt; 0 -&gt; 1</code>).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], queries = [0,1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1,-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Each value in <code>nums</code> is unique, so no index shares the same value as the queried element. This results in -1 for all queries.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= queries[i] &lt; nums.length</code></li>
</ul>


## Solution

```java
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
        }
        for (int ele : queries) {
            int val = nums[ele];
            TreeSet<Integer> current = new TreeSet<>();
            current = map.get(val);
            int dist = Integer.MAX_VALUE / 10;
            if (current.higher(ele) != null) {
                dist = Math.min(dist, Math.abs(ele - current.higher(ele)));
                int new_dist = n - 1 - current.last() + (ele + 1);
                dist = Math.min(dist, new_dist);
            }
            if (current.lower(ele) != null) {
                int last_ind = current.first();
                int new_dist = n - (ele + 1) + (last_ind + 1);
                dist = Math.min(dist, new_dist);
                dist = Math.min(dist, Math.abs(ele - current.lower(ele)));
            }
            if (dist == Integer.MAX_VALUE / 10) res.add(-1);
            else res.add(dist);
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

