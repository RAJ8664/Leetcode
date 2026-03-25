class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        n, m = len(grid), len(grid[0])
        
        row_sum, col_sum = [0] * n, [0] * m
        
        curr_sum = 0
        for i in range(n):
            for j in range(m): 
               curr_sum += grid[i][j] 
            row_sum[i] = curr_sum

        curr_sum = 0
        for j in range(m):
            for i in range(n): 
                curr_sum += grid[i][j]
            col_sum[j] = curr_sum 

        for i in range(0, n - 1): 
            up_sum = row_sum[i]
            down_sum = row_sum[n - 1] - up_sum
            if (up_sum == down_sum): 
                return True

        for j in range(0, m - 1): 
            left_sum = col_sum[j]
            right_sum = col_sum[m - 1] - left_sum
            if (left_sum == right_sum): 
                return True

        return False 