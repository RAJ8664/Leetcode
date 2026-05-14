class Solution:
    def minFlips(self, s: str) -> int:
        freq = [0, 0]
        for c in s:
            freq[int(c)] += 1
        if freq[0] == 0 or freq[1] == 0: return 0
        return min(freq[0], freq[1] - (int(s[0]) and int(s[-1])) - 1)