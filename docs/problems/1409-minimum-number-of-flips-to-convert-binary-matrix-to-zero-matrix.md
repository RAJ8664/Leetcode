# 1409. Minimum Number Of Flips To Convert Binary Matrix To Zero Matrix

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1409-minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix">1409. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix</a></h2><h3>Hard</h3><hr><p>Given a <code>m x n</code> binary matrix <code>mat</code>. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing <code>1</code> to <code>0</code> and <code>0</code> to <code>1</code>). A pair of cells are called neighbors if they share one edge.</p>

<p>Return the <em>minimum number of steps</em> required to convert <code>mat</code> to a zero matrix or <code>-1</code> if you cannot.</p>

<p>A <strong>binary matrix</strong> is a matrix with all cells equal to <code>0</code> or <code>1</code> only.</p>

<p>A <strong>zero matrix</strong> is a matrix with all cells equal to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/11/28/matrix.png" style="width: 409px; height: 86px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Given matrix is a zero matrix. We do not need to change it.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,0,0],[1,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Given matrix cannot be a zero matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 3</code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


---

## Solution

```java

import java.util.ArrayList;

class Solution {
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "row=" + row +
                   ", col=" + col +
                   '}';
        }
    }
    private ArrayList<ArrayList<Pair >> choose;
    public int minFlips(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        choose = new ArrayList<>();

        solve(0, 0, new ArrayList<>(), mat);

        int mini = Integer.MAX_VALUE;
        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (ArrayList<Pair> curr : choose) {
            int arr[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    arr[i][j] = mat[i][j];
            }
            for (Pair p : curr) {
                int currRow = p.row, currCol = p.col;
                arr[currRow][currCol] = 1 - arr[currRow][currCol];
                for (int dire[] : dir) {
                    int newRow = currRow + dire[0], newCol = currCol + dire[1];
                    if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m)
                        arr[newRow][newCol] = 1 - arr[newRow][newCol];
                }
            }
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag == false)
                    break;
            }
            if (flag == true)
                mini = Math.min(mini, curr.size());
        }
        if (mini == Integer.MAX_VALUE)
            return -1;
        return mini;
    }

    private void solve(int row, int col, ArrayList<Pair> current, int mat[][]) {
        if (row == mat.length - 1 && col == mat[0].length) {
            choose.add(new ArrayList<>(current));
            return;
        }

        if (row == mat.length && col == mat[0].length) {
            choose.add(new ArrayList<>(current));
            return;
        }

        if (col == mat[0].length) {
            row++;
            col = 0;
        }

        current.add(new Pair(row, col));
        solve(row, col + 1, current, mat);

        current.remove(current.size() - 1);
        solve(row, col + 1, current, mat);
    }

}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

