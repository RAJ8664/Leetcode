class Solution:
    def isMiddleElementUnique(self, nums: list[int]) -> bool:
        n = len(nums)
        middle_ele = nums[n // 2]
        count = 0
        for ele in nums:
            if ele == middle_ele:
                count += 1
        return count == 1
        
        