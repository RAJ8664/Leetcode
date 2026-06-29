class Solution:
    def maximizeWin(self, arr: List[int], k: int) -> int:
        n = len(arr)
        dp = [0] * (n + 1)
        res = right = 0
        
        for left in range(n):
            while arr[right] + k < arr[left]: right += 1
            dp[left + 1] = max(dp[left], left - right + 1)
            res = max(res, left - right + 1 + dp[right])

        return res 
        