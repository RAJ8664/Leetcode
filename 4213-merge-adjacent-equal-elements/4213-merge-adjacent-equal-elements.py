class Solution:
    def mergeAdjacent(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = []
        for ele in nums:
            if len(res) == 0:
                res.append(ele)
            else:
                res.append(ele)
                while len(res) > 1 and res[len(res) - 2] == res[len(res) - 1]:
                    popped = res[-1]
                    res.pop((len(res) - 1))
                    res.pop(len(res) - 1)
                    res.append(2 * popped)
        return res

