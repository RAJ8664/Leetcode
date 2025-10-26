# 2047. Find A Peak Element Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-a-peak-element-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2047-find-a-peak-element-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-a-peak-element-ii">2047. Find a Peak Element II</a></h2><h3>Medium</h3><hr><p>A <strong>peak</strong> element in a 2D grid is an element that is <strong>strictly greater</strong> than all of its <strong>adjacent </strong>neighbors to the left, right, top, and bottom.</p>

<p>Given a <strong>0-indexed</strong> <code>m x n</code> matrix <code>mat</code> where <strong>no two adjacent cells are equal</strong>, find <strong>any</strong> peak element <code>mat[i][j]</code> and return <em>the length 2 array </em><code>[i,j]</code>.</p>

<p>You may assume that the entire matrix is surrounded by an <strong>outer perimeter</strong> with the value <code>-1</code> in each cell.</p>

<p>You must write an algorithm that runs in <code>O(m log(n))</code> or <code>O(n log(m))</code> time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/06/08/1.png" style="width: 206px; height: 209px;" /></p>

<pre>
<strong>Input:</strong> mat = [[1,4],[3,2]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong>&nbsp;Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2021/06/07/3.png" style="width: 254px; height: 257px;" /></strong></p>

<pre>
<strong>Input:</strong> mat = [[10,20,15],[21,30,14],[7,16,32]]
<strong>Output:</strong> [1,1]
<strong>Explanation:</strong>&nbsp;Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
	<li>No two adjacent cells are equal.</li>
</ul>


---

## Solution

```java
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int maxR = -1, maxEle = -1;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > maxEle) {
                    maxEle = mat[i][mid];
                    maxR = i;
                }
            }   

            int left = -1, right = -1;
            if (mid - 1 >= 0) 
                left = mat[maxR][mid - 1];
            if (mid + 1 < m) 
                right = mat[maxR][mid + 1];
            if (mat[maxR][mid] > left && mat[maxR][mid] > right) 
                return new int[]{maxR, mid};
            else {
                if (right > mat[maxR][mid])
                    low = mid + 1;
                else 
                    high = mid - 1;
            } 
        }
        return new int[]{-1, -1};
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

