class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        res = n
        op = [0, 0]

        for i in range(n):
            op[(ord(s[i]) ^ i) & 1] += 1
        
        for i in range(n): 
            curr = ord(s[i])
            op[(curr ^ i) & 1] -= 1
            op[(curr ^ (n + i)) & 1] += 1
            res = min(res, min(op))
        
        return res