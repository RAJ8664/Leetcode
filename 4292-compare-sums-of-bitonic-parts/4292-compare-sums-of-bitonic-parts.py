class Solution:
    def compareBitonicSums(self, nums: list[int]) -> int:
        n = len(nums)
        
        maxi = nums[0]
        idx = 0
        for i in range(n):
            if nums[i] > maxi:
                maxi = nums[i]
                idx = i

        left_sum, right_sum = 0, 0
        for i in range(0, idx + 1):
            left_sum += nums[i]
        for i in range(idx, n):
            right_sum += nums[i]
        
        if left_sum > right_sum: return 0
        elif left_sum < right_sum: return 1
        return -1
             
        