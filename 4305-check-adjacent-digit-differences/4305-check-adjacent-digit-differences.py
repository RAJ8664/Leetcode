class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        n = len(s)
        for i in range(n - 1):
            if abs(int(s[i]) - int(s[i + 1])) > 2:
                return False
        return True
        