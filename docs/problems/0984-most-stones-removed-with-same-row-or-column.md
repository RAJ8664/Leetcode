# 984. Most Stones Removed With Same Row Or Column

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0984-most-stones-removed-with-same-row-or-column){ .md-button }

---

<h2><a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column">984. Most Stones Removed with Same Row or Column</a></h2><h3>Medium</h3><hr><p>On a 2D plane, we place <code>n</code> stones at some integer coordinate points. Each coordinate point may have at most one stone.</p>

<p>A stone can be removed if it shares either <strong>the same row or the same column</strong> as another stone that has not been removed.</p>

<p>Given an array <code>stones</code> of length <code>n</code> where <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the location of the <code>i<sup>th</sup></code> stone, return <em>the largest possible number of stones that can be removed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> [0,0] is the only stone on the plane, so you cannot remove it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 1000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>No two stones are at the same coordinate point.</li>
</ul>


---

## Solution

```java
class Solution {
    static class DSU {
        int parent[], size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int Leader(int u) {
            if (u == parent[u])
                return u;
            return parent[u] = Leader(parent[u]);
        }
        public void merge(int u, int v) {
            u = Leader(u);
            v = Leader(v);
            if (u == v)
                return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            size[u] += size[v];
            parent[v] = u;
        } 
    }
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DSU dsu = new DSU(n + 1);

        HashMap<Integer, Integer> rowId = new HashMap<>();
        HashMap<Integer, Integer> colId = new HashMap<>();
        HashSet<Integer> rowOccurred = new HashSet<>();
        HashSet<Integer> colOccurred = new HashSet<>();
        
        int currId = 1;
        for (int i = 0; i < stones.length; i++) {
            int x = stones[i][0], y = stones[i][1];
            
            if (rowOccurred.contains(x) && colOccurred.contains(y)) {
                //This stones made two components to join;
                int getRowId = rowId.get(x);
                int getColId = colId.get(y);

                dsu.merge(getRowId, getColId);
                dsu.merge(currId, getRowId);

                rowOccurred.add(x);
                colOccurred.add(y);                
            }

            else if (rowOccurred.contains(x)) {
                // It matches only with one of the rows;
                int getRowId = rowId.get(x);

                dsu.merge(getRowId, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

                colId.put(y, currId);
            }
            else if (colOccurred.contains(y)) {
                // It matches with only one of the col;
                int getColId = colId.get(y);

                dsu.merge(getColId, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

                rowId.put(x, currId);
            }
            else {
                // It does not matches with any of the row's or col's

                colId.put(y, currId);
                rowId.put(x, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

            }
            currId++;
        }
        int count = 0;
        for (int i = 1; i < currId; i++) {
            if (dsu.Leader(i) == i) {
                count += dsu.size[i] - 1;
            }
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

