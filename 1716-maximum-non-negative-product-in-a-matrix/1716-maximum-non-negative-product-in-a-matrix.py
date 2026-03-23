class Pair:
    def __init__(self, row, col, val):
        self.row = row
        self.col = col
        self.val = val

    def __repr__(self): 
        return f"({self.row}, {self.col}, {self.val})"

class Solution:
    MOD = 10**9 + 7
    
    def maxProductPath(self, grid):
        n, m = len(grid), len(grid[0])
        directions = [(0, 1), (1, 0)]
        dp = [[[float('-inf'), float('inf')] * 2 for _ in range(m)] for _ in range(n)]
        dp[0][0][0] = grid[0][0]
        dp[0][0][1] = grid[0][0]
        q = deque()
        q.append(Pair(0, 0, grid[0][0]))
        while q:
            curr = q.popleft()
            r, c, val = curr.row, curr.col, curr.val
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                if 0 <= nr < n and 0 <= nc < m:
                    new_val = val * grid[nr][nc]
                    if new_val < 0:
                        if dp[nr][nc][1] > new_val:
                            dp[nr][nc][1] = new_val
                            q.append(Pair(nr, nc, new_val))
                    else:
                        if dp[nr][nc][0] < new_val:
                            dp[nr][nc][0] = new_val
                            q.append(Pair(nr, nc, new_val))

        if (dp[n - 1][m - 1][0] == float('-inf')): return -1
        ans = dp[n - 1][m - 1][0] % self.MOD
        if ans < 0:
            ans = -1
        if ans == -1:
            for i in range(n):
                for j in range(m):
                    if grid[i][j] == 0:
                        return 0
        if (ans is None): return -1
        return ans