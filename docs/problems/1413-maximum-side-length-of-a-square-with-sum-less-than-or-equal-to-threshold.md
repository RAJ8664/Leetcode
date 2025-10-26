# 1413. Maximum Side Length Of A Square With Sum Less Than Or Equal To Threshold

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1413-maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold">1413. Maximum Side Length of a Square with Sum Less than or Equal to Threshold</a></h2><h3>Medium</h3><hr><p>Given a <code>m x n</code> matrix <code>mat</code> and an integer <code>threshold</code>, return <em>the maximum side-length of a square with a sum less than or equal to </em><code>threshold</code><em> or return </em><code>0</code><em> if there is no such square</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/12/05/e1.png" style="width: 335px; height: 186px;" />
<pre>
<strong>Input:</strong> mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum side length of square with sum less than 4 is 2 as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>0 &lt;= mat[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= threshold &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private int pref[][];
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length, m = mat[0].length;

        buildPref(mat);
        
        int low = 0, high = Math.min(n, m), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(n, m, mid, threshold)) {
                ans = mid ;
                low = mid + 1;
            }
            else 
                high = mid - 1;
        } 
        return ans + 1;
    }
    private boolean ok(int n, int m, int target, int threshold) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + target < n && j + target < m) {
                    int r1 = i, c1 = j, r2 = i + target, c2 = j + target;
                    if (query(r1, c1, r2, c2) <= threshold) 
                        return true;
                }
            }
        }
        return false;
    }
    private void buildPref(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        pref = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0) 
                    pref[i][j] += pref[i - 1][j];
                if (j - 1 >= 0) 
                    pref[i][j] += pref[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0) 
                    pref[i][j] -= pref[i - 1][j - 1];
                pref[i][j] += arr[i][j];
            }
        }
    }
    private int query(int r1, int c1, int r2, int c2) {
        int total = pref[r2][c2];
        if (r1 - 1 >= 0)
            total -= pref[r1 - 1][c2];
        if (c1 - 1 >= 0)
            total -= pref[r2][c1 - 1];
        if (r1 - 1 >= 0 && c1 - 1 >= 0)
            total += pref[r1 - 1][c1 - 1];
        return total;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

