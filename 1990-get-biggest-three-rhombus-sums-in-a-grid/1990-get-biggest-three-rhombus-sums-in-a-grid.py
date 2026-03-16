class Solution:
    def getBiggestThree(self, grid):
        m = len(grid)
        n = len(grid[0])
        sums = set()
        for i in range(m):
            for j in range(n):
                for d in range(26):
                    if i - d < 0 or j - d < 0 or i + d >= m or j + d >= n:
                        break
                    temp = 0
                    if d == 0:
                        temp = grid[i][j]
                    else:
                        temp = grid[i - d][j] + grid[i + d][j] + grid[i][j - d] + grid[i][j + d]
                        for t in range(1, d):
                            temp += grid[i + t][j + d - t]
                            temp += grid[i + t][j - d + t]
                            temp += grid[i - t][j + d- t]
                            temp += grid[i - t][j - d + t]
                    sums.add(temp)

        ans = sorted(sums, reverse=True)
        return ans[:3]