class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        MOD = 10 ** 9 + 7
        r = r - l
        dp = [1] * (r + 1)
        
        for i in range(1, n):
            pre1, pre2 = 0, 0
            if i % 2 == 1:
                for v in range(0, r + 1):
                    pre2 += dp[v]
                    dp[v] = pre1
                    pre1 = pre2 % MOD
            else:
                for v in range(r, -1, -1):
                    pre2 = pre1 + dp[v]
                    dp[v] = pre1
                    pre1 = pre2 % MOD

        res = 0
        for ele in dp:
            res = (res + ele) % MOD
        return (res * 2) % MOD

        