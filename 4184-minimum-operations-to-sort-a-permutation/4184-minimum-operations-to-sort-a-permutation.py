class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        
        count = 0
        for i in range(n):
            if nums[i] > nums[(i + 1) % n]:
                count += 1

        if count == 0:
            return 0

        min_idx = nums.index(0)
        if count == 1:
            res = min_idx
            res = min(res, 2 + n - res)
            return res

        if n - count == 1:
            res = n - min_idx
            res = min(res, min_idx + 2)
            return res

        return -1
            
        