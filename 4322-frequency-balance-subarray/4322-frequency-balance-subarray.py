class Solution:
    def getLength(self, nums: List[int]) -> int:
        n = len(nums)
        res = 1
        for i in range(n):
            mp1 = {}
            mp2 = {}
            for j in range(i, n):
                if nums[j] in mp1:
                    mp2[mp1[nums[j]]] -= 1
                    if mp2[mp1[nums[j]]] == 0:
                        del mp2[mp1[nums[j]]] 
                    mp1[nums[j]] += 1
                else:
                    mp1[nums[j]] = 1
                if mp1[nums[j]] in mp2:
                    mp2[mp1[nums[j]]] += 1
                else:
                    mp2[mp1[nums[j]]] = 1
                if len(mp1) == 1:
                    res = max(res, j - i + 1)
                if len(mp2) == 2 and min(mp2.keys()) * 2 == max(mp2.keys()):
                    res = max(res, j - i + 1)
        return res
        