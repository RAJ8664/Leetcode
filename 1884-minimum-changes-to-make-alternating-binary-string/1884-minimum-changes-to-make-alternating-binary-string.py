class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)
        count = 0
        if n == 1: 
            return 0
        for i in range(n): 
            if (i % 2 == 0 and s[i] == '1'): 
                count += 1
            if (i % 2 == 1 and s[i] == '0'): 
                count += 1
        return min(count, n - count) 
        