class Solution:
    def maximumScore(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if n == 1:
            return 0
        dp0 = [0] * (n + 1)
        dp1 = [0] * (n + 1)
        for j in range(1, n):
            new_dp0 = [0] * (n + 1)
            new_dp1 = [0] * (n + 1)
            for i in range(n + 1):
                prev = 0
                curr = sum(grid[x][j] for x in range(i))
                for y in range(n + 1):
                    if y > 0 and y <= i:
                        curr -= grid[y - 1][j]
                    if j > 0 and y > i:
                        prev += grid[y - 1][j - 1]
                    new_dp0[y] = max(new_dp0[y], prev + dp0[i], dp1[i])
                    new_dp1[y] = max(new_dp1[y], curr + dp1[i], curr + prev + dp0[i])
            dp0, dp1 = new_dp0, new_dp1
        return max(dp1)