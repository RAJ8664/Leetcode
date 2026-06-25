class Solution:
    def checkValidGrid(self, grid: List[List[int]]) -> bool:
        n, m = len(grid), len(grid[0])
        curr_row, curr_col = 0, 0

        if grid[curr_row][curr_col] != 0:
            return False

        target = 1
        while target <= n * n - 1:
            flag = False
            if curr_row + 2 < n and curr_col + 1 < m and grid[curr_row + 2][curr_col + 1] == target:
                curr_row, curr_col = curr_row + 2, curr_col + 1
                target += 1
                flag = True

            elif curr_row + 2 < n and curr_col - 1 >= 0 and grid[curr_row + 2][curr_col - 1] == target:
                curr_row, curr_col = curr_row + 2, curr_col - 1
                target += 1
                flag = True
            
            elif curr_row - 2 >= 0 and curr_col + 1 < m and grid[curr_row - 2][curr_col + 1] == target:
                curr_row, curr_col = curr_row - 2, curr_col + 1
                target += 1
                flag = True

            elif curr_row - 2 >= 0 and curr_col - 1 >= 0 and grid[curr_row - 2][curr_col - 1] == target:
                curr_row, curr_col = curr_row - 2, curr_col - 1
                target += 1
                flag = True

            elif curr_row + 1 < n and curr_col + 2 < m and grid[curr_row + 1][curr_col + 2] == target:
                curr_row, curr_col = curr_row + 1, curr_col + 2
                target += 1
                flag = True

            elif curr_row + 1 < n and curr_col - 2 >= 0 and grid[curr_row + 1][curr_col - 2] == target:
                curr_row, curr_col = curr_row + 1, curr_col - 2
                target += 1
                flag = True 

            elif curr_row - 1 >= 0 and curr_col + 2 < m and grid[curr_row - 1][curr_col + 2] == target:
                curr_row, curr_col = curr_row - 1, curr_col + 2
                target += 1
                flag = True

            elif curr_row - 1 >= 0 and curr_col - 2 >= 0 and grid[curr_row - 1][curr_col - 2] == target:
                curr_row, curr_col = curr_row - 1, curr_col - 2
                target += 1
                flag = True

            if not flag:
                return False

        return True