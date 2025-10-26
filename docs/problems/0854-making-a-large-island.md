# 854. Making A Large Island

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/making-a-large-island/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0854-making-a-large-island){ .md-button }

---

<h2><a href="https://leetcode.com/problems/making-a-large-island">854. Making A Large Island</a></h2><h3>Hard</h3><hr><p>You are given an <code>n x n</code> binary matrix <code>grid</code>. You are allowed to change <strong>at most one</strong> <code>0</code> to be <code>1</code>.</p>

<p>Return <em>the size of the largest <strong>island</strong> in</em> <code>grid</code> <em>after applying this operation</em>.</p>

<p>An <strong>island</strong> is a 4-directionally connected group of <code>1</code>s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1],[1,0]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>Change the 0 to 1 and make the island bigger, only one island with area = 4.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1],[1,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Can&#39;t change any 0 to 1, only one island with area = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    private int vis[][];
    private int id[][];
    private int size[];
    private int currId;
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
    }    
    public int largestIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        vis = new int[n][m];
        id = new int[n][m];
        size = new int[n * m + 1];
        currId = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] == 1) {
                    BFS(i, j, grid);
                    currId++;
                }
            }
        }

        int maxi = 1;
        for (int x : size) 
            maxi = Math.max(maxi, x);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    HashSet<Integer> ids = new HashSet<>();
                   
                    if (i - 1 >= 0) ids.add(id[i - 1][j]);
                    if (j - 1 >= 0) ids.add(id[i][j - 1]);
                    if (i + 1 < n) ids.add(id[i + 1][j]);
                    if (j + 1 < m) ids.add(id[i][j + 1]);

                    int compSum = 1;
                    for (int curIds : ids) {
                        compSum += size[curIds]; 
                    }
                    maxi = Math.max(maxi, compSum);
                }
            }
        }
        return maxi;
    }

    private void BFS(int startRow, int startCol, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int compSize = 1;
    
        q.offer(new Pair(startRow, startCol));
        id[startRow][startCol] = currId;
        vis[startRow][startCol] = 1;
        int dir[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0 && vis[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
                    vis[newRow][newCol] = 1;
                    q.offer(new Pair(newRow, newCol));
                    id[newRow][newCol] = currId;
                    compSize++;
                }
            }
        }
        size[currId] = compSize;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

