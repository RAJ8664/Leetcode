class Solution:
    def maxTotal(self, nums: List[int], s: str) -> int:
        n = len(nums)
        res = 0
        min_ele = float('inf')
        for i in range(n - 1, -1, -1):
            if s[i] == '1':
                res += nums[i]
                min_ele = min(min_ele, nums[i])
            else:
                if i + 1 < n and s[i + 1] == '1':
                    res += nums[i]
                    min_ele = min(min_ele, nums[i])
                    res -= min_ele
                min_ele = float('inf')
        return res 