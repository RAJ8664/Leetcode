# 407. Trapping Rain Water Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/trapping-rain-water-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0407-trapping-rain-water-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/trapping-rain-water-ii">407. Trapping Rain Water II</a></h2><h3>Hard</h3><hr><p>Given an <code>m x n</code> integer matrix <code>heightMap</code> representing the height of each unit cell in a 2D elevation map, return <em>the volume of water it can trap after raining</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/08/trap1-3d.jpg" style="width: 361px; height: 321px;" />
<pre>
<strong>Input:</strong> heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/08/trap2-3d.jpg" style="width: 401px; height: 321px;" />
<pre>
<strong>Input:</strong> heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
<strong>Output:</strong> 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == heightMap.length</code></li>
	<li><code>n == heightMap[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= heightMap[i][j] &lt;= 2 * 10<sup>4</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Tuple {
        int row, col, cost;
        public Tuple(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + " " + cost + ")";
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.cost, second.cost);
        }
    }
    
    public int trapRainWater(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int vis[][] = new int[n][m];
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++) {
            pq.offer(new Tuple(i, 0, arr[i][0]));
            pq.offer(new Tuple(i, m - 1, arr[i][m - 1]));
            vis[i][0] = 1;
            vis[i][m - 1] = 1;
        }      

        for (int j = 0; j < m; j++) {
            pq.offer(new Tuple(0, j, arr[0][j]));
            pq.offer(new Tuple(n - 1, j, arr[n - 1][j]));
            vis[0][j] = 1;
            vis[n - 1][j] = 1;
        }   

        int res = 0;
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currCost = pq.peek().cost;
            pq.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newRow >= 0 && newCol < m && newCol >= 0 && vis[newRow][newCol] == 0) {
                    vis[newRow][newCol] = 1;
                    res += Math.max(0, currCost - arr[newRow][newCol]);
                    pq.offer(new Tuple(newRow, newCol, Math.max(arr[newRow][newCol], currCost)));
                }
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

