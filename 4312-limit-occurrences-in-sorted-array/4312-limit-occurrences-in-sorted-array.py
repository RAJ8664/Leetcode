class Solution:
    def limitOccurrences(self, nums: list[int], k: int) -> list[int]:
        res = []
        count = [0] * 101
        for ele in nums:
            if count[ele] < k:
                count[ele] += 1
                res.append(ele)
        return res
        
        