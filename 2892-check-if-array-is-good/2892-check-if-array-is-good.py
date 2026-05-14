class Solution:
    def isGood(self, nums: List[int]) -> bool:
        n = len(nums)
        if n == 1: return False
        nums.sort()
        base = nums[n - 1]
        for i in range(n - 1):
            if nums[i] != i + 1:
                return False
        if nums[n - 1] != base or nums[n - 2] != base: return False
        return True
        