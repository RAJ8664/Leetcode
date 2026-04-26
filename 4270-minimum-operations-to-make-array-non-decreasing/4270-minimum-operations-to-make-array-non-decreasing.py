class Solution:
    def minOperations(self, nums: list[int]) -> int:
        n = len(nums)
        res = 0
        for i in range(n - 1):
            res += max(0, nums[i] - nums[i + 1]) 
        
        return res