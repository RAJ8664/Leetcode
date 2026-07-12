class Solution:
    def solve(self, prev_considered_col, curr_col, dp, grid, limit) -> int:
        if curr_col >= len(grid[0]):
            return 0

        if dp[prev_considered_col + 1][curr_col] != -1:
            return dp[prev_considered_col + 1][curr_col]

        if prev_considered_col == -1:
            # we have two options here -> (Either start from this col or let it go)

            op1 = 1 + self.solve(curr_col, curr_col + 1, dp, grid, limit)
            op2 = 0 + self.solve(prev_considered_col, curr_col + 1, dp, grid, limit)

            dp[prev_considered_col + 1][curr_col] = max(op1, op2)
            return dp[prev_considered_col + 1][curr_col]

        else:
            # We have two options here as well, but need to be careful where we are allowed to consider this col or not, because 
            # we have already considered some prev col, so we need to check if we can take this col or not.

            op1 = 0 + self.solve(prev_considered_col, curr_col + 1, dp, grid, limit) # Let it go
            op2 = 0 # Check if we can consider this col

            flag = True
            for i in range(len(grid)):
                if abs(grid[i][prev_considered_col] - grid[i][curr_col]) > limit:
                    flag = False
                    break

            if flag:
                op2 = 1 + self.solve(curr_col, curr_col + 1, dp, grid, limit)

            dp[prev_considered_col + 1][curr_col] = max(op1, op2)
            return dp[prev_considered_col + 1][curr_col]

    def maxConsistentColumns(self, grid: List[List[int]], limit: int) -> int:
        n, m = len(grid), len(grid[0])

        dp = [[-1 for _ in range(m)] for _ in range(m + 1)]

        return self.solve(-1, 0, dp, grid, limit) 