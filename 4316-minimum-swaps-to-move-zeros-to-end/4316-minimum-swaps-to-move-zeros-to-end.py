class Solution:
    def minimumSwaps(self, nums: list[int]) -> int:
        n = len(nums)
        zeroes = 0
        for ele in nums:
            if ele == 0:
                zeroes += 1
        idx = n - 1
        count = 0
        while zeroes > 0:
            if nums[idx] != 0:
                count += 1
            zeroes -= 1
            idx -= 1

        return count 
            
        