# 51. N Queens

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/n-queens/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0051-n-queens){ .md-button }

---

<h2><a href="https://leetcode.com/problems/n-queens">51. N-Queens</a></h2><h3>Hard</h3><hr><p>The <strong>n-queens</strong> puzzle is the problem of placing <code>n</code> queens on an <code>n x n</code> chessboard such that no two queens attack each other.</p>

<p>Given an integer <code>n</code>, return <em>all distinct solutions to the <strong>n-queens puzzle</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>Each solution contains a distinct board configuration of the n-queens&#39; placement, where <code>&#39;Q&#39;</code> and <code>&#39;.&#39;</code> both indicate a queen and an empty space, respectively.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> [[&quot;.Q..&quot;,&quot;...Q&quot;,&quot;Q...&quot;,&quot;..Q.&quot;],[&quot;..Q.&quot;,&quot;Q...&quot;,&quot;...Q&quot;,&quot;.Q..&quot;]]
<strong>Explanation:</strong> There exist two distinct solutions to the 4-queens puzzle as shown above
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[&quot;Q&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>


---

## Solution

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char board[][] = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) board[i][j] = '.';
        }
        List<List<String>> res = new ArrayList<>();
        solve(board, 0, res);
        return res;
    }
    private static void solve(char board[][] , int current_row, List<List<String>> res) {
        if(current_row == board.length) {
            List<String> temp = new ArrayList<>();
            for(int i = 0; i < board.length; i++) {
                String current = "";
                for(int j = 0; j < board.length; j++) current += board[i][j];
                temp.add(current);
            }
            res.add(new ArrayList<>(temp));
            return;  
        }
        for(int j = 0; j < board.length; j++) {
            if(check(current_row, j , board)) {
                board[current_row][j] = 'Q';
                solve(board, current_row + 1, res);
                board[current_row][j] = '.';
            }
        }
    }
    private static boolean check(int row, int col, char board[][]) {
        int n = board.length;
        for(int j = 0; j < n; j++) if(board[row][j] == 'Q') return false;
        for(int i = 0; i < n; i++) if(board[i][col] == 'Q') return false;
        int cr = row, cc = col;
        while(cr < n && cc < n) {
            if(board[cr][cc] == 'Q') return false;
            cr++;
            cc++;
        }
        cr = row; cc = col;
        while(cr < n && cc >= 0) {
            if(board[cr][cc] == 'Q') return false;
            cr++;
            cc--;
        }
        cr = row; cc = col;
        while(cr >= 0 && cc < n) {
            if(board[cr][cc] == 'Q') return false;
            cr--;
            cc++;
        }
        cr = row; cc = col;
        while(cr >= 0 && cc >= 0) {
            if(board[cr][cc] == 'Q') return false;
            cr--;
            cc--;
        }
        return true;  
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

