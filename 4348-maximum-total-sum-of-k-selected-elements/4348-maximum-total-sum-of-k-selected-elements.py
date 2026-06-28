class Solution:
    def maxSum(self, nums: list[int], k: int, mul: int) -> int:
        n = len(nums)
        res = 0
        
        nums.sort()
        nums.reverse()

        for i in range(k):
            if mul > 0:
                res = res + nums[i] * mul
                mul -= 1
            else:
                res = res + nums[i]

        return res
        