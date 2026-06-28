class Solution:
    def solve(self, arr: List[int], k: int, mul: bool) -> int:
        dp0 = dp1 = dp2 = -10**30
        ans = -10**30
        
        for x in arr:
            if mul:
                y = x * k
            else:
                if x >= 0:
                    y = math.floor(x / k)
                else:
                    y = math.ceil(x / k)
            d0 = max(x, dp0 + x)
            d1 = max(y, dp0 + y, dp1 + y)
            d2 = max(dp1 + x, dp2 + x)
            
            dp0, dp1, dp2 = d0, d1, d2
            ans = max(ans, dp1, dp2)

        return ans
        
    def maxSubarraySum(self, nums: List[int], k: int) -> int:
        return max(self.solve(nums, k, True), self.solve(nums, k, False))
        