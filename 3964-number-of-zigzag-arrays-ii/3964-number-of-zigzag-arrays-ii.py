class Solution:
    MOD = 10**9 + 7

    def multiply(self, A, B):
        sz = len(A)
        C = [[0] * sz for _ in range(sz)]

        for i in range(sz):
            for k in range(sz):
                if A[i][k] == 0:
                    continue
                for j in range(sz):
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % self.MOD

        return C

    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        # Nice One!!!!
        if n == 1:
            return r - l + 1

        k = r - l + 1
        sz = 2 * k

        M = [[0] * sz for _ in range(sz)]

        for i in range(k):
            for j in range(i):
                M[i][k + j] = 1

            for j in range(i + 1, k):
                M[k + i][j] = 1

        res = [[0] * sz for _ in range(sz)]

        for i in range(sz):
            res[i][i] = 1

        p = n - 1

        while p > 0:
            if p % 2 == 1:
                res = self.multiply(res, M)

            M = self.multiply(M, M)
            p //= 2

        ans = 0

        for i in range(sz):
            for j in range(sz):
                ans = (ans + res[i][j]) % self.MOD

        return ans