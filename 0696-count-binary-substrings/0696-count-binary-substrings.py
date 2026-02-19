class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        n = len(s)
        count = 0
        prev = 0
        temp = 1
        for i in range(1, n):
            if s[i] == s[i - 1]:
                temp += 1
            else:
                prev = temp
                temp = 1
            if temp <= prev:
                count += 1
        return count
        