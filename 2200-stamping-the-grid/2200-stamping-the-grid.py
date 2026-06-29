from typing import List

class Solution:
    def possibleToStamp(self, grid: List[List[int]], stampHeight: int, stampWidth: int) -> bool:
        n, m = len(grid), len(grid[0])

        pref = [[0] * m for _ in range(n)]
        for i in range(n):
            for j in range(m):
                pref[i][j] = grid[i][j]
                if i > 0:
                    pref[i][j] += pref[i - 1][j]
                if j > 0:
                    pref[i][j] += pref[i][j - 1]
                if i > 0 and j > 0:
                    pref[i][j] -= pref[i - 1][j - 1]

        def query(r1, c1, r2, c2):
            total = pref[r2][c2]
            if r1 > 0:
                total -= pref[r1 - 1][c2]
            if c1 > 0:
                total -= pref[r2][c1 - 1]
            if r1 > 0 and c1 > 0:
                total += pref[r1 - 1][c1 - 1]
            return total

        diff = [[0] * m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                r2 = i + stampHeight - 1
                c2 = j + stampWidth - 1

                if r2 >= n or c2 >= m:
                    continue

                if query(i, j, r2, c2) == 0:
                    diff[i][j] += 1
                    if c2 + 1 < m:
                        diff[i][c2 + 1] -= 1
                    if r2 + 1 < n:
                        diff[r2 + 1][j] -= 1
                    if r2 + 1 < n and c2 + 1 < m:
                        diff[r2 + 1][c2 + 1] += 1

        for i in range(n):
            for j in range(1, m):
                diff[i][j] += diff[i][j - 1]

        for j in range(m):
            for i in range(1, n):
                diff[i][j] += diff[i - 1][j]

        for i in range(n):
            for j in range(m):
                if grid[i][j] == 0 and diff[i][j] == 0:
                    return False

        return True