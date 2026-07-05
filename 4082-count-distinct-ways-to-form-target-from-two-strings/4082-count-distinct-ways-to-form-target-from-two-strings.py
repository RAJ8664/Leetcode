class Solution:
    def interleaveCharacters(self, word1: str, word2: str, target: str) -> int:
        n, m, t = len(word1), len(word2), len(target)

        MOD = 10 ** 9 + 7

        @lru_cache(None)
        def solve(i: int, j: int, k: int) -> int:
            if k >= len(target):
                return 1 if i > 0 and j > 0 else 0

            count = 0
            for x in range(i, len(word1)):
                if word1[x] == target[k]:
                    count = (count + solve(x + 1, j, k + 1)) % MOD

            for x in range(j, len(word2)):
                if word2[x] == target[k]:
                    count = (count + solve(i, x + 1, k + 1)) % MOD

            return count 

        return solve(0, 0, 0)