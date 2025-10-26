# 3251. Maximum Area Of Longest Diagonal Rectangle

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3251-maximum-area-of-longest-diagonal-rectangle){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle">3251. Maximum Area of Longest Diagonal Rectangle</a></h2><h3>Easy</h3><hr><p>You are given a 2D <strong>0-indexed </strong>integer array <code>dimensions</code>.</p>

<p>For all indices <code>i</code>, <code>0 &lt;= i &lt; dimensions.length</code>, <code>dimensions[i][0]</code> represents the length and <code>dimensions[i][1]</code> represents the width of the rectangle<span style="font-size: 13.3333px;"> <code>i</code></span>.</p>

<p>Return <em>the <strong>area</strong> of the rectangle having the <strong>longest</strong> diagonal. If there are multiple rectangles with the longest diagonal, return the area of the rectangle having the <strong>maximum</strong> area.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dimensions = [[9,3],[8,6]]
<strong>Output:</strong> 48
<strong>Explanation:</strong> 
For index = 0, length = 9 and width = 3. Diagonal length = sqrt(9 * 9 + 3 * 3) = sqrt(90) &asymp;<!-- notionvc: 882cf44c-3b17-428e-9c65-9940810216f1 --> 9.487.
For index = 1, length = 8 and width = 6. Diagonal length = sqrt(8 * 8 + 6 * 6) = sqrt(100) = 10.
So, the rectangle at index 1 has a greater diagonal length therefore we return area = 8 * 6 = 48.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dimensions = [[3,4],[4,3]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Length of diagonal is the same for both which is 5, so maximum area = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dimensions.length &lt;= 100</code></li>
	<li><code><font face="monospace">dimensions[i].length == 2</font></code></li>
	<li><code><font face="monospace">1 &lt;= dimensions[i][0], dimensions[i][1] &lt;= 100</font></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int areaOfMaxDiagonal(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int maxi = Integer.MIN_VALUE;
        int currMaxDiagonal = Integer.MIN_VALUE / 10;
        for (int i = 0; i < n; i++) {
            int diagonal = (arr[i][0] * arr[i][0] + arr[i][1] * arr[i][1]);
            if (diagonal > currMaxDiagonal) {
                maxi = arr[i][1] * arr[i][0];
                currMaxDiagonal = diagonal;
            }
            else if (diagonal == currMaxDiagonal) {
                if (arr[i][0] * arr[i][1] > maxi) {
                    maxi = arr[i][0] * arr[i][1];
                }
            }
        }
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

