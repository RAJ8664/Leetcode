class Solution:
    def firstMatchingIndex(self, s: str) -> int:
        n = len(s)
        
        for i in range(n // 2 + 1): 
            if s[i] == s[n - 1 - i]: 
                return i

        return -1; 