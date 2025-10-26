# 1628. Count Submatrices With All Ones

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-submatrices-with-all-ones/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1628-count-submatrices-with-all-ones){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-submatrices-with-all-ones">1628. Count Submatrices With All Ones</a></h2><h3>Medium</h3><hr><p>Given an <code>m x n</code> binary matrix <code>mat</code>, <em>return the number of <strong>submatrices</strong> that have all ones</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/10/27/ones1-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> mat = [[1,0,1],[1,1,0],[1,1,0]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> 
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2. 
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/10/27/ones2-grid.jpg" style="width: 324px; height: 245px;" />
<pre>
<strong>Input:</strong> mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> 
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3. 
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2. 
There are 2 rectangles of side 3x1. 
There is 1 rectangle of side 3x2. 
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 150</code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int val;
        int index;
        public Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
        @Override
        public String toString() {
            return "(" + val + " " + index + ")";
        }
    }
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int current_state[] = new int[m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) current_state[j] = 0;
                else current_state[j]++;
            }
            res += find_sum_of_all_heights_of_histogram(current_state);
        }
        return res;
    }

    private int find_sum_of_all_heights_of_histogram(int arr[]) {
        int n = arr.length;
        Stack<Pair> st = new Stack<>();
        int prev_smallest[] = new int[n];
        int next_smallest[] = new int[n];

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().val >= current) st.pop();
            if (st.size() == 0) prev_smallest[i] = -1;
            else prev_smallest[i] = st.peek().index;
            st.add(new Pair(current, i));
        }
        st.clear();

        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().val >= current) st.pop();
            if (st.size() == 0) next_smallest[i] = -1;
            else next_smallest[i] = st.peek().index;
            st.add(new Pair(current, i));
        }

        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            int next_smaller_index = next_smallest[i];
            int prev_smaller_index = prev_smallest[i];
            if (prev_smaller_index == -1) res[i] = current * (i + 1);
            else {
                res[i] = res[prev_smaller_index];
                res[i] += current * (i - prev_smaller_index);
            }
        }
        int total = 0;
        for (int ele : res) total += ele;
        return total;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

