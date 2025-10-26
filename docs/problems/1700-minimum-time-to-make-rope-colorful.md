# 1700. Minimum Time To Make Rope Colorful

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1700-minimum-time-to-make-rope-colorful){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-time-to-make-rope-colorful">1700. Minimum Time to Make Rope Colorful</a></h2><h3>Medium</h3><hr><p>Alice has <code>n</code> balloons arranged on a rope. You are given a <strong>0-indexed</strong> string <code>colors</code> where <code>colors[i]</code> is the color of the <code>i<sup>th</sup></code> balloon.</p>

<p>Alice wants the rope to be <strong>colorful</strong>. She does not want <strong>two consecutive balloons</strong> to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it <strong>colorful</strong>. You are given a <strong>0-indexed</strong> integer array <code>neededTime</code> where <code>neededTime[i]</code> is the time (in seconds) that Bob needs to remove the <code>i<sup>th</sup></code> balloon from the rope.</p>

<p>Return <em>the <strong>minimum time</strong> Bob needs to make the rope <strong>colorful</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/12/13/ballon1.jpg" style="width: 404px; height: 243px;" />
<pre>
<strong>Input:</strong> colors = &quot;abaac&quot;, neededTime = [1,2,3,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In the above image, &#39;a&#39; is blue, &#39;b&#39; is red, and &#39;c&#39; is green.
Bob can remove the blue balloon at index 2. This takes 3 seconds.
There are no longer two consecutive balloons of the same color. Total time = 3.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/12/13/balloon2.jpg" style="width: 244px; height: 243px;" />
<pre>
<strong>Input:</strong> colors = &quot;abc&quot;, neededTime = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The rope is already colorful. Bob does not need to remove any balloons from the rope.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/12/13/balloon3.jpg" style="width: 404px; height: 243px;" />
<pre>
<strong>Input:</strong> colors = &quot;aabaa&quot;, neededTime = [1,2,3,4,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Bob will remove the balloons at indices 0 and 4. Each balloons takes 1 second to remove.
There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == colors.length == neededTime.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= neededTime[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>colors</code> contains only lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int pref[] = new int[n];
        pref[0] = neededTime[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + neededTime[i];
        
        SparseMax sp = new SparseMax(neededTime);
        
        int totalTime = 0, i = 0;
        while (i < n) {
            if (i == n - 1) break;
            if (colors.charAt(i) != colors.charAt(i + 1)) {
                i++;
                continue;
            }
            
            int j = i;
            while (j + 1 < n && colors.charAt(j + 1) == colors.charAt(i)) {
                j++;
            }
            
            /* From i --> j we have same characters */
            totalTime += pref[j];
            if (i - 1 >= 0) 
                totalTime -= pref[i - 1];
            totalTime -= sp.query(i, j);
            
            i = j + 1;
        }
        return totalTime;
    }
    
    static class SparseMax {
        int[][] st;      
        int[] log;     
        int n;
        public SparseMax(int[] arr) {
            n = arr.length;
            log = new int[n + 1];
            buildLog();

            int k = log[n] + 1;
            st = new int[n][k];

            for (int i = 0; i < n; i++) {
                st[i][0] = arr[i];
            }

            for (int j = 1; j < k; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        private void buildLog() {
            log[1] = 0;
            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }
        }
        public int query(int L, int R) {
            int j = log[R - L + 1];
            return Math.max(st[L][j], st[R - (1 << j) + 1][j]);
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

