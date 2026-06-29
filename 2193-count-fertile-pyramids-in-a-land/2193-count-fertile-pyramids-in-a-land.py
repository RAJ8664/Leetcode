class Solution:
    """
    def get_pref(self, grid: List[List[int]]):
        n, m = len(grid), len(grid[0])

        pref = [[0 for _ in range(m)] for _ in range(n)]

        for i in range(n):
            for j in range(m):
                pref[i][j] = grid[i][j]
                if i > 0:
                    pref[i][j] += pref[i - 1][j]

                if j > 0:
                    pref[i][j] += pref[i][j - 1]
    
                if i > 0 and j > 0:
                    pref[i][j] -= pref[i - 1][j - 1] 

        return pref
    """

    """
    def get_sum_query(self, pref, r1, c1, r2, c2) -> int:
        ans = pref[r2][c2]
        if r1 > 0:
            ans -= pref[r1 - 1][c2]
    
        if c1 > 0:
            ans -= pref[r2][c1 - 1]
    
        if r1 > 0 and c1 > 0:
            ans += pref[r1 - 1][c1 - 1]
         
        return ans
    """

    """
    def ok(self, target: int, pref, grid, row: int, col: int) -> bool:
        n, m = len(grid), len(grid[0])

        r1, c1 = row + target, col - target
        r2, c2 = row + target, col + target

        if r1 >= n or r2 >= n or c1 < 0 or c2 >= m:
            return False

        while True:
            if r1 == r2 and c1 == c2:
                break

            sum = self.get_sum_query(pref, r1, c1, r2, c2)

            if sum != c2 - c1 + 1:
                return False

            r1, c1 = r1 - 1, c1 + 1
            r2, c2 = r2 - 1, c2 - 1 

        return True
    """

    """
    def get_count(self, grid, pref):
        n, m = len(grid), len(grid[0])
        
        count = 0
        for i in range(n):
            for j in range(m):
                if grid[i][j] == 0: continue
                low, high = 1, n
                ans = -1
                while low <= high:
                    mid = low + (high - low) // 2
                    if self.ok(mid, pref, grid, i, j):
                        ans = mid
                        low = mid + 1
                    else:
                        high = mid - 1
                
                if ans != -1:
                    count += ans

        return count
    """

    def rotate_grid(self, grid):
        return [row[::-1] for row in grid[::-1]]

    def optimal_count(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])
        dp = [[0] * m for _ in range(n)]
        count = 0
        for i in range(n - 2, -1, -1):
            for j in range(1, m - 1):
                if grid[i][j] == 0:
                    continue
                if grid[i + 1][j - 1] and grid[i + 1][j] and grid[i + 1][j + 1]:
                    dp[i][j] = min(
                        dp[i + 1][j - 1],
                        dp[i + 1][j],
                        dp[i + 1][j + 1],
                    ) + 1
                count += dp[i][j]

        return count 

    def countPyramids(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])

        count = 0
        count += self.optimal_count(grid)
        grid = self.rotate_grid(grid)
        count += self.optimal_count(grid)

        return count 