class Solution:

    def buildPref(self, pref, grid): 
        n = len(grid)
        m = len(grid[0])
        for i in range(n):
            for j in range(m):
                pref[i][j] = grid[i][j]
                if i > 0: pref[i][j] += pref[i - 1][j]
                if j > 0: pref[i][j] += pref[i][j - 1]
                if i > 0 and j > 0: pref[i][j] -= pref[i - 1][j - 1]


    def query(self, x1, y1, x2, y2, pref) -> int: 
        res = pref[x2][y2]
        if x1 > 0: res -= pref[x1 - 1][y2]
        if y1 > 0: res -= pref[x2][y1 - 1]
        if x1 > 0 and y1 > 0: res += pref[x1 - 1][y1 - 1]
        return res 

    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        n = len(grid)
        m = len(grid[0])

        pref = [[0] * m for _ in range(n)]

        self.buildPref(pref, grid) 

        count = 0
        for i in range(n): 
            for j in range(m): 
                if i == 0 and j == 0: 
                    if grid[0][0] <= k: count += 1
                    continue 
                total = self.query(0, 0, i, j, pref)
                if total <= k: count += 1

        return count 