class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        max_pref = nums[0]
        min_suff = [0] * n
        min_suff[n - 1] = nums[n - 1]
        
        for i in range(n - 2, -1, -1):
            min_suff[i] = min(min_suff[i + 1], nums[i])

        for i in range(n):
            max_pref = max(max_pref, nums[i])
            if max_pref - min_suff[i] <= k:
                return i
                 
        return -1
        