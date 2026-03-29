class Solution:
    def canBeEqual(self, s1: str, s2: str) -> bool:
        a1 = list(s1)
        for i in range(2):
            if a1[i] != s2[i]:
                a1[i], a1[i + 2] = a1[i + 2], a1[i]
        return all(a1[i] == s2[i] for i in range(4))