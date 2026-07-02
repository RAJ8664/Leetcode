class Solution:
    def solve(self, row: int, col: int, matrix: List[List[int]], dp) -> int:
        if dp[row][col] != -1:
            return dp[row][col]

        count = 1
        dir = [[-1, 0], [1, 0], [0, -1], [0, 1]]

        for dire in dir:
            new_row, new_col = row + dire[0], col + dire[1]
            if new_row < len(matrix) and new_col < len(matrix[0]) and new_row >= 0 and new_col >= 0:
                if matrix[new_row][new_col] > matrix[row][col]:
                    count = max(count,  1 + self.solve(new_row, new_col, matrix, dp))
        
        dp[row][col] = count
        return count

    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        n, m = len(matrix), len(matrix[0])

        dp = [[-1 for _ in range(m)] for _ in range(n)] 

        longest = 1
        for i in range(n):
            for j in range(m):
                longest = max(longest, self.solve(i, j, matrix, dp))

        return longest
        
                
         