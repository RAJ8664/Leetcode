class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        dp = [-1] * n

        def solve(idx: int) -> int:
            if dp[idx] != -1:
                return dp[idx]
            
            maxi = 1
            for i in range(idx + 1, min(n, idx + d + 1)):
                if arr[i] >= arr[idx]:
                    break
                maxi = max(maxi, 1 + solve(i))
            for i in range(idx - 1, max(-1, idx - d - 1), -1):
                if arr[i] >= arr[idx]:
                    break
                maxi = max(maxi, 1 + solve(i))
            
            dp[idx] = maxi
            return dp[idx]
        
        res = 1
        for i in range(n):
            res = max(res, solve(i)) 

        return res    
            
            
        