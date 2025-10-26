# 769. Largest Plus Sign

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-plus-sign/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0769-largest-plus-sign){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-plus-sign">769. Largest Plus Sign</a></h2><h3>Medium</h3><hr><p>You are given an integer <code>n</code>. You have an <code>n x n</code> binary grid <code>grid</code> with all values initially <code>1</code>&#39;s except for some indices given in the array <code>mines</code>. The <code>i<sup>th</sup></code> element of the array <code>mines</code> is defined as <code>mines[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> where <code>grid[x<sub>i</sub>][y<sub>i</sub>] == 0</code>.</p>

<p>Return <em>the order of the largest <strong>axis-aligned</strong> plus sign of </em>1<em>&#39;s contained in </em><code>grid</code>. If there is none, return <code>0</code>.</p>

<p>An <strong>axis-aligned plus sign</strong> of <code>1</code>&#39;s of order <code>k</code> has some center <code>grid[r][c] == 1</code> along with four arms of length <code>k - 1</code> going up, down, left, and right, and made of <code>1</code>&#39;s. Note that there could be <code>0</code>&#39;s or <code>1</code>&#39;s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for <code>1</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/06/13/plus1-grid.jpg" style="width: 404px; height: 405px;" />
<pre>
<strong>Input:</strong> n = 5, mines = [[4,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In the above grid, the largest plus sign can only be of order 2. One of them is shown.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/06/13/plus2-grid.jpg" style="width: 84px; height: 85px;" />
<pre>
<strong>Input:</strong> n = 1, mines = [[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no plus sign, so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= mines.length &lt;= 5000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; n</code></li>
	<li>All the pairs <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
import java.util.Arrays;

class Solution {
    private int rowPref[][];
    private int colPref[][];
    private int arr[][];
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        rowPref = new int[n + 1][n + 1];
        colPref = new int[n + 1][n + 1];

        arr = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                arr[i][j] = 1;
        }
        for (int curr[] : mines)
            arr[curr[0]][curr[1]] = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
                rowPref[i][j] = sum;
            }
        }

        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i][j];
                colPref[j][i] = sum;
            }
        }

        int low = 1, high = n, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, n)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private boolean ok(int k, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    int rightSum = 0, leftSum = 0, upSum = 0, downSum = 0;

                    rightSum += rowPref[i][Math.min(j + k - 1, n - 1)];
                    if (j - 1 >= 0)
                        rightSum -= rowPref[i][j - 1];

                    leftSum += rowPref[i][j];
                    if (j - k >= 0)
                        leftSum -= rowPref[i][j - k];

                    upSum += colPref[j][i];
                    if (i - k >= 0)
                        upSum -= colPref[j][i - k];

                    downSum += colPref[j][Math.min(i + k - 1, n - 1)];
                    if (i - 1 >= 0)
                        downSum -= colPref[j][i - 1];
                    if (rightSum >= k && leftSum >= k && upSum >= k && downSum >= k)
                        return true;
                }
            }
        }
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

