class Solution:
    def maxValidPairSum(self, nums: list[int], k: int) -> int:
        n = len(nums)
        max_suff = [0] * n

        max_suff[n - 1] = nums[n - 1]
        for i in range(n - 2, -1, -1):
            max_suff[i] = max(max_suff[i + 1], nums[i])

        maxi = 0
        for i in range(n):
            if i + k < n:
                maxi = max(maxi, nums[i] + max_suff[i + k]) 
        
        return maxi