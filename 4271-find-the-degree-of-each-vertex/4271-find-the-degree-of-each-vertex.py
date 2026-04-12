class Solution:
    def findDegrees(self, matrix: list[list[int]]) -> list[int]:
        n, m = len(matrix), len(matrix[0])
        adj = [[] for _ in range(n)]
        for i in range(n):
            for j in range(m):
                if matrix[i][j] == 1:
                    adj[i].append(j)
                    adj[j].append(i)
        res = [0] * n
        for i in range(n):
            res[i] = len(adj[i]) // 2
        return res
        