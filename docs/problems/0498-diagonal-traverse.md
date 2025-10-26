# 498. Diagonal Traverse

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/diagonal-traverse/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0498-diagonal-traverse){ .md-button }

---

<h2><a href="https://leetcode.com/problems/diagonal-traverse">498. Diagonal Traverse</a></h2><h3>Medium</h3><hr><p>Given an <code>m x n</code> matrix <code>mat</code>, return <em>an array of all the elements of the array in a diagonal order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/10/diag1-grid.jpg" style="width: 334px; height: 334px;" />
<pre>
<strong>Input:</strong> mat = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [1,2,4,7,5,3,6,8,9]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,2],[3,4]]
<strong>Output:</strong> [1,2,3,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<Integer> res;
    private int count;
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        res = new ArrayList<>();
        count = 0;
        for (int j = 0; j < m; j++) {
            fill(0, j, mat);
            count++;
        }
        for (int i = 1; i < n; i++) {
            fill(i, m - 1, mat);
            count++;
        }
        int ans[] = new int[res.size()];
        for (int i = 0; i < res.size(); i++) 
            ans[i] = res.get(i);
        return ans; 
    }
    private void fill(int startRow, int startCol, int arr[][]) {
        int n = arr.length, m = arr[0].length;
        ArrayList<Integer> temp = new ArrayList<>();
        while (startRow < n && startCol >= 0) {
            temp.add(arr[startRow][startCol]);
            startRow++;
            startCol--;
        }
        if (count % 2 == 0) {
            Collections.reverse(temp);
        }
        for (int ele : temp) 
            res.add(ele);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

