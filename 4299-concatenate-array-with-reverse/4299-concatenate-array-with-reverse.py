class Solution:
    def concatWithReverse(self, nums: list[int]) -> list[int]:
        n = len(nums)
        res = []
        for ele in nums:
            res.append(ele)
        for i in range(n - 1, -1, -1):
            res.append(nums[i])
        return res
         
        