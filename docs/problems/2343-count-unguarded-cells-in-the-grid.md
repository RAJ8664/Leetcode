# 2343. Count Unguarded Cells In The Grid

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2343-count-unguarded-cells-in-the-grid){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-unguarded-cells-in-the-grid">2343. Count Unguarded Cells in the Grid</a></h2><h3>Medium</h3><hr><p>You are given two integers <code>m</code> and <code>n</code> representing a <strong>0-indexed</strong> <code>m x n</code> grid. You are also given two 2D integer arrays <code>guards</code> and <code>walls</code> where <code>guards[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> and <code>walls[j] = [row<sub>j</sub>, col<sub>j</sub>]</code> represent the positions of the <code>i<sup>th</sup></code> guard and <code>j<sup>th</sup></code> wall respectively.</p>

<p>A guard can see <b>every</b> cell in the four cardinal directions (north, east, south, or west) starting from their position unless <strong>obstructed</strong> by a wall or another guard. A cell is <strong>guarded</strong> if there is <strong>at least</strong> one guard that can see it.</p>

<p>Return<em> the number of unoccupied cells that are <strong>not</strong> <strong>guarded</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/03/10/example1drawio2.png" style="width: 300px; height: 204px;" />
<pre>
<strong>Input:</strong> m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/03/10/example2drawio.png" style="width: 200px; height: 201px;" />
<pre>
<strong>Input:</strong> m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= guards.length, walls.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= guards.length + walls.length &lt;= m * n</code></li>
	<li><code>guards[i].length == walls[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, row<sub>j</sub> &lt; m</code></li>
	<li><code>0 &lt;= col<sub>i</sub>, col<sub>j</sub> &lt; n</code></li>
	<li>All the positions in <code>guards</code> and <code>walls</code> are <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.row == row && current.col == col;
        }
        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    private HashSet<Pair> bad_cell;
    private HashSet<Pair> wall;
    private HashSet<Pair> guard;
    public int countUnguarded(int n, int m, int[][] guards, int[][] walls) {
        bad_cell = new HashSet<>();
        wall = new HashSet<>();
        guard = new HashSet<>();
        for (int curr[] : guards) guard.add(new Pair(curr[0], curr[1]));
        for (int curr[] : walls) wall.add(new Pair(curr[0], curr[1]));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (guard.contains(new Pair(i, j))) fill_bad(i, j, n, m);
            }
        }       
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!wall.contains(new Pair(i, j)) && !bad_cell.contains(new Pair(i, j)) && !guard.contains(new Pair(i, j))) {
                    count++;
                }
            }
        } 
        return count;
    }

    private void fill_bad(int row, int col, int n , int m) {
        int cr = row, cc = col;
        //up;
        cr--;
        while (cr >= 0) {
            if (!wall.contains(new Pair(cr, cc))) bad_cell.add(new Pair(cr, cc));
            else break;
            if (guard.contains(new Pair(cr, cc))) break;
            cr--;
        }
        //down
        cr = row + 1;
        cc = col;
        while (cr < n) {
            if (!wall.contains(new Pair(cr, cc))) bad_cell.add(new Pair(cr, cc));
            else break;
            if (guard.contains(new Pair(cr, cc))) break;
            cr++;
        }
        //left;
        cr = row;
        cc = col - 1;
        while (cc >= 0) {
            if (!wall.contains(new Pair(cr, cc))) bad_cell.add(new Pair(cr, cc));
            else break;
            if (guard.contains(new Pair(cr, cc))) break;
            cc--;
        }
        //right;
        cr = row;
        cc = col + 1;
        while (cc < m) {
            if (!wall.contains(new Pair(cr, cc))) bad_cell.add(new Pair(cr, cc));
            else break;
            if (guard.contains(new Pair(cr, cc))) break;
            cc++;
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

