class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        dp = [False] * n
        dp[0] = True
        can = 0
        for i in range(1, n):
            if i - minJump >= 0 and dp[i - minJump]:
                can += 1
            if i - maxJump - 1 >= 0 and dp[i - maxJump - 1]:
                can -= 1
            dp[i] = (can > 0 and s[i] == '0')
        return dp[n - 1]
        