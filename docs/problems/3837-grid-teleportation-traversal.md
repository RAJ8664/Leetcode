# 3837. Grid Teleportation Traversal

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/grid-teleportation-traversal/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3837-grid-teleportation-traversal){ .md-button }

---

<h2><a href="https://leetcode.com/problems/grid-teleportation-traversal">3837. Grid Teleportation Traversal</a></h2><h3>Medium</h3><hr><p>You are given a 2D character grid <code>matrix</code> of size <code>m x n</code>, represented as an array of strings, where <code>matrix[i][j]</code> represents the cell at the intersection of the <code>i<sup>th</sup></code> row and <code>j<sup>th</sup></code> column. Each cell is one of the following:</p>

<ul>
	<li><code>&#39;.&#39;</code> representing an empty cell.</li>
	<li><code>&#39;#&#39;</code> representing an obstacle.</li>
	<li>An uppercase letter (<code>&#39;A&#39;</code>-<code>&#39;Z&#39;</code>) representing a teleportation portal.</li>
</ul>

<p>You start at the top-left cell <code>(0, 0)</code>, and your goal is to reach the bottom-right cell <code>(m - 1, n - 1)</code>. You can move from the current cell to any adjacent cell (up, down, left, right) as long as the destination cell is within the grid bounds and is not an obstacle<strong>.</strong></p>

<p>If you step on a cell containing a portal letter and you haven&#39;t used that portal letter before, you may instantly teleport to any other cell in the grid with the same letter. This teleportation does not count as a move, but each portal letter can be used<strong> at most </strong>once during your journey.</p>

<p>Return the <strong>minimum</strong> number of moves required to reach the bottom-right cell. If it is not possible to reach the destination, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [&quot;A..&quot;,&quot;.A.&quot;,&quot;...&quot;]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/03/15/example04140.png" style="width: 151px; height: 151px;" /></p>

<ul>
	<li>Before the first move, teleport from <code>(0, 0)</code> to <code>(1, 1)</code>.</li>
	<li>In the first move, move from <code>(1, 1)</code> to <code>(1, 2)</code>.</li>
	<li>In the second move, move from <code>(1, 2)</code> to <code>(2, 2)</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [&quot;.#...&quot;,&quot;.#.#.&quot;,&quot;.#.#.&quot;,&quot;...#.&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/03/15/ezgifcom-animated-gif-maker.gif" style="width: 251px; height: 201px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == matrix.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= n == matrix[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>matrix[i][j]</code> is either <code>&#39;#&#39;</code>, <code>&#39;.&#39;</code>, or an uppercase English letter.</li>
	<li><code>matrix[0][0]</code> is not an obstacle.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

class Solution {
    private HashMap<Character, ArrayList<Pair >> map;
    static class State {
        int row, col, move;
        int freq[];
        public State(int row, int col, int move, int[] freq) {
            this.row = row;
            this.col = col;
            this.move = move;
            this.freq = freq;
        }
    }
    static class customSort implements Comparator<State> {
        @Override
        public int compare(State first, State second) {
            return Integer.compare(first.move, second.move);
        }
    }
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair)o;
                return p.row == row && p.col == col;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    public int minMoves(String[] matrix) {
        int n = matrix.length, m = matrix[0].length();
        map = new HashMap<>();
        char arr[][] = new char[n][matrix[0].length()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                arr[i][j] = matrix[i].charAt(j);
                if (!map.containsKey(arr[i][j]))
                    map.put(arr[i][j], new ArrayList<>());
                map.get(arr[i][j]).add(new Pair(i, j));
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>(new customSort());
        pq.offer(new State(0, 0, 0, new int[26]));
        int dist[][] = new int[n][m];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));
        dist[0][0] = 0;
        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currMove = pq.peek().move;
            int currFreq[] = pq.peek().freq;
            pq.poll();

            if (arr[currRow][currCol] != '.') {
                if (currFreq[arr[currRow][currCol] - 'A'] == 0) {
                    int newFreq[] = new int[26];
                    newFreq = currFreq;
                    newFreq[arr[currRow][currCol] - 'A'] = 1;
                    ArrayList<Pair> cells = new ArrayList<>();
                    cells = map.get(arr[currRow][currCol]);
                    for (Pair x : cells) {
                        if (dist[x.row][x.col] > currMove) {
                            dist[x.row][x.col] = currMove;
                            pq.offer(new State(x.row, x.col, currMove, newFreq));
                        }
                    }
                }
            }

            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || arr[newRow][newCol] == '#')
                    continue;
                if (dist[newRow][newCol] > currMove + 1) {
                    dist[newRow][newCol] = currMove + 1;
                    pq.offer(new State(newRow, newCol, currMove + 1, currFreq));
                }
            }
        }
        if (dist[n - 1][m - 1] == (int)(1e9))
            return -1;
        return dist[n - 1][m - 1];
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

