class Solution:
    def numSteps(self, s: str) -> int:
        n = len(s)
        count = 0
        carry = 0
        for i in range(n - 1, 0, -1):
            if (int(s[i])) + carry % 2 == 1: 
                count += 2
                carry = 1
            else: 
                count += 1
        return count + carry  