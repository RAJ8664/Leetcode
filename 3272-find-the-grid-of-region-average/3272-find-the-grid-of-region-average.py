class Solution:
    def is_region(self, grid, curr_row, curr_col, threshold):
        n, m = len(grid), len(grid[0])

        if curr_row + 2 >= n or curr_col + 2 >= m:
            return (0, 0)

        total = 0
        for i in range(curr_row, curr_row + 3):
            for j in range(curr_col, curr_col + 3):
                total += grid[i][j]

                if i < curr_row + 2:
                    if abs(grid[i][j] - grid[i + 1][j]) > threshold:
                        return (0, 0)

                if j < curr_col + 2:
                    if abs(grid[i][j] - grid[i][j + 1]) > threshold:
                        return (0, 0)

        return (1, total // 9)

    def resultGrid(self, image: List[List[int]], threshold: int) -> List[List[int]]:
        n, m = len(image), len(image[0])

        res = [[[] for _ in range(m)] for _ in range(n)]

        for i in range(n):
            for j in range(m):
                region, avg = self.is_region(image, i, j, threshold)
                if region:
                    for x in range(i, i + 3):
                        for y in range(j, j + 3):
                            res[x][y].append(avg)

        ans = [[0] * m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                if not res[i][j]:
                    ans[i][j] = image[i][j]
                else:
                    ans[i][j] = sum(res[i][j]) // len(res[i][j])

        return ans