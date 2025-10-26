# 1860. Find Kth Largest Xor Coordinate Value

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1860-find-kth-largest-xor-coordinate-value){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-kth-largest-xor-coordinate-value">1860. Find Kth Largest XOR Coordinate Value</a></h2><h3>Medium</h3><hr><p>You are given a 2D <code>matrix</code> of size <code>m x n</code>, consisting of non-negative integers. You are also given an integer <code>k</code>.</p>

<p>The <strong>value</strong> of coordinate <code>(a, b)</code> of the matrix is the XOR of all <code>matrix[i][j]</code> where <code>0 &lt;= i &lt;= a &lt; m</code> and <code>0 &lt;= j &lt;= b &lt; n</code> <strong>(0-indexed)</strong>.</p>

<p>Find the <code>k<sup>th</sup></code> largest value <strong>(1-indexed)</strong> of all the coordinates of <code>matrix</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 1
<strong>Output:</strong> 7
<strong>Explanation:</strong> The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int pref[][];
    static class customSort implements Comparator<Integer> {
        @Override
        public int compare(Integer first, Integer second) {
            return Integer.compare(first, second);
        }
    }
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;        
        
        buildPref(matrix);

        PriorityQueue<Integer> pq = new PriorityQueue<>(new customSort());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pq.offer(pref[i][j]);
                if (pq.size() > k) 
                    pq.poll(); 
            }
        }
        return pq.poll();
    }
    private void buildPref(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        pref = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0) 
                    pref[i][j] ^= pref[i - 1][j];
                if (j - 1 >= 0)
                    pref[i][j] ^= pref[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0) 
                    pref[i][j] ^= pref[i - 1][j - 1];
                pref[i][j] ^= arr[i][j];
            }
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

