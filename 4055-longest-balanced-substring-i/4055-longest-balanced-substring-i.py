class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        maxi = 0
        for i in range(0, n):
            count = dict()
            for j in range(i, n):
                count[s[j]] = count.get(s[j], 0) + 1
                st = set(list(count.values()))
                if len(st) == 1:
                    maxi = max(maxi, j - i + 1)

        return maxi

