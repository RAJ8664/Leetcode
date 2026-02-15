class Solution:
    
    def solve(self, s, i, j, flag): 
        if i < 0 and j >= len(s):
            return 0
        if i < 0 or j >= len(s):
            if flag == 1: 
                return 1
            else: 
                return 0
        if s[i] != s[j] and flag == 0: 
            return 0
        res = 0
        if s[i] == s[j]:
            res = self.solve(s, i - 1, j + 1, flag) + 2
        else:
            res = max(self.solve(s, i, j + 1, 1 - flag), self.solve(s, i - 1, j, 1 - flag)) + 1
        return res

    def almostPalindromic(self, s: str) -> int:
        n = len(s)
        res = 2
        for i in range(n - 1):
            op1 = self.solve(s, i, i, 1) - 1
            op2 = self.solve(s, i, i + 1, 1)
            res = max(res, op1, op2)
        return res        