# 1550. Find The Kth Smallest Sum Of A Matrix With Sorted Rows

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1550-find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows">1550. Find the Kth Smallest Sum of a Matrix With Sorted Rows</a></h2><h3>Hard</h3><hr><p>You are given an <code>m x n</code> matrix <code>mat</code> that has its rows sorted in non-decreasing order and an integer <code>k</code>.</p>

<p>You are allowed to choose <strong>exactly one element</strong> from each row to form an array.</p>

<p>Return <em>the </em><code>k<sup>th</sup></code><em> smallest array sum among all possible arrays</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,3,11],[2,4,6]], k = 5
<strong>Output:</strong> 7
<strong>Explanation:</strong> Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,3,11],[2,4,6]], k = 9
<strong>Output:</strong> 17
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
<strong>Output:</strong> 9
<strong>Explanation:</strong> Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.  
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat.length[i]</code></li>
	<li><code>1 &lt;= m, n &lt;= 40</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 5000</code></li>
	<li><code>1 &lt;= k &lt;= min(200, n<sup>m</sup>)</code></li>
	<li><code>mat[i]</code> is a non-decreasing array.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        List<Integer> curr = new ArrayList<>();
        for (int val : mat[0])
            curr.add(val);
        for (int i = 1; i < n; i++)
            curr = mergeKSmallest(curr, mat[i], k);
        return curr.get(k - 1);
    }

    private ArrayList<Integer> mergeKSmallest(List<Integer> a, int[] b, int k) {
        int m = a.size(), n = b.length;
        ArrayList<Integer> res = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        Set<String> visited = new HashSet<>();

        pq.offer(new int[] {a.get(0) + b[0], 0, 0});
        visited.add("0,0");

        while (!pq.isEmpty() && res.size() < k) {
            int[] top = pq.poll();
            int sum = top[0], i = top[1], j = top[2];
            res.add(sum);

            if (i + 1 < m && visited.add((i + 1) + "," + j)) {
                pq.offer(new int[] {a.get(i + 1) + b[j], i + 1, j});
            }
            if (j + 1 < n && visited.add(i + "," + (j + 1))) {
                pq.offer(new int[] {a.get(i) + b[j + 1], i, j + 1});
            }
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

