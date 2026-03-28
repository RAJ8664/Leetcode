class Solution:
    def minAbsoluteDifference(self, nums: list[int]) -> int:
        n = len(nums)
        
        res = 10 ** 9
        for i in range(n):
            for j in range(i + 1, n):
                if (nums[i] == 1 and nums[j] == 2) or (nums[i] == 2 and nums[j] == 1): 
                    res = min(res, abs(i - j))

        if res == 10 ** 9: return -1
        return res 
        
        