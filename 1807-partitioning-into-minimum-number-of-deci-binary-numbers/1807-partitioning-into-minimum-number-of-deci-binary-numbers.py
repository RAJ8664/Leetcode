class Solution:
    def minPartitions(self, n: str) -> int:
        maxi = 0
        for i in range(len(n)): 
            maxi = max(maxi, int(n[i]))
        return maxi 