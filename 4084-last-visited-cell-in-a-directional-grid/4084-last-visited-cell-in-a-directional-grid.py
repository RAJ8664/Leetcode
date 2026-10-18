class Solution:
    def lastVisitedCell(self, grid: list[list[str]]) -> list[int]:
        n, m = len(grid), len(grid[0])

        vis = [[0 for _ in range(m)] for _ in range(n)]

        curr_row, curr_col = 0, 0
        while True:
            vis[curr_row][curr_col] = 1 
            at_row, at_col = curr_row, curr_col
            if grid[curr_row][curr_col] == 'L':
                curr_col -= 1
            elif grid[curr_row][curr_col] == 'R':
                curr_col += 1
            elif grid[curr_row][curr_col] == 'U':
                curr_row -= 1
            elif grid[curr_row][curr_col] == 'D':
                curr_row += 1

            if curr_row >= n or curr_col >= m or curr_row < 0 or curr_col < 0:
                return [at_row, at_col]

            if vis[curr_row][curr_col] == 1:
                return [at_row, at_col]