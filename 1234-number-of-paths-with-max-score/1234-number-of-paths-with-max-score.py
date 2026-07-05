class Solution:
    def pathsWithMaxScore(self, board):
        MOD = 10**9 + 7
        n = len(board)
        m = len(board[0])

        matrix = [list(row) for row in board]
        matrix[0][0] = '0'
        matrix[n - 1][m - 1] = '0'

        NEG_INF = -10**9
        sum_dp = [[NEG_INF] * (m + 1) for _ in range(n + 1)]
        ways = [[0] * (m + 1) for _ in range(n + 1)]

        directions = [(0, -1), (-1, 0), (-1, -1)]

        sum_dp[n - 1][m - 1] = 0
        ways[n - 1][m - 1] = 1

        for i in range(n - 1, -1, -1):
            for j in range(m - 1, -1, -1):
                if matrix[i][j] == 'X':
                    continue

                for dr, dc in directions:
                    ni, nj = i + dr, j + dc

                    if (
                        0 <= ni < n
                        and 0 <= nj < m
                        and matrix[ni][nj] != 'X'
                    ):
                        new_sum = sum_dp[i][j] + int(matrix[ni][nj])

                        if new_sum > sum_dp[ni][nj]:
                            sum_dp[ni][nj] = new_sum
                            ways[ni][nj] = ways[i][j]
                        elif new_sum == sum_dp[ni][nj]:
                            ways[ni][nj] = (ways[ni][nj] + ways[i][j]) % MOD

        if ways[0][0] == 0:
            return [0, 0]

        return [sum_dp[0][0], ways[0][0]]
        