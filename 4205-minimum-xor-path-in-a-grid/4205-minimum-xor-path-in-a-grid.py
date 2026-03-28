class Solution:
    
    def solve(self, row, col, grid, xor, dp):
        if row >= len(grid) or col >= len(grid[0]): return

        if dp[row][col][xor] == 1: return

        dp[row][col][xor] = 1

        if row + 1 < len(grid):
            self.solve(row + 1, col, grid, xor ^ grid[row + 1][col], dp)

        if (col + 1 < len(grid[0])): 
            self.solve(row, col + 1, grid, xor ^ grid[row][col + 1], dp)        


    def minCost(self, grid: list[list[int]]) -> int:
        n, m = len(grid), len(grid[0])
        dp = [[[0] * 1024 for _ in range(m)] for _ in range(n)]

        self.solve(0, 0, grid, grid[0][0], dp)

        for i in range(1024): 
            if dp[n - 1][m - 1][i] == 1: return i
        
        return 0

     
        