class Solution:
    def createGrid(self, m: int, n: int, k: int) -> List[str]:
        grid = []

        for i in range(m):
            if i == 0:
                grid.append(list("." * n))
            else:
                grid.append(list("#" * (n - 1) + "."))

        if k >= 2:
            if n >= 2 and m >= 2:
                grid[1][n - 2] = "."
            else:
                return []

        if k >= 3:
            if n >= 3 and n >= m:
                grid[1][n - 3] = "."
            elif m >= 3 and m >= n:
                grid[2][n - 2] = "."
            else:
                return []

        if k == 4:
            if n >= 4 and n >= m:
                grid[1][n - 4] = "."
            elif m >= 4 and m >= n:
                grid[3][n - 2] = "."
            elif m == 3 and n == 3:
                return ["..#", "...", "#.."]
            else:
                return []

        return ["".join(row) for row in grid]


        