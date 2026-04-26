class Solution:
    def findValidElements(self, nums: list[int]) -> list[int]:
        n = len(nums)
        maxi = nums[0]
        res = []
        for i in range(n):
            flag = 1
            if i == 0 or i == n - 1:
                res.append(nums[i])
            else:
                for j in range(i + 1, n):
                    if nums[i] <= nums[j]:
                        flag = 0
                        break
                if flag: res.append(nums[i])
                else:
                    flag = 1
                    for j in range(0, i):
                        if nums[i] <= nums[j]:
                            flag = 0
                            break
                    if flag: res.append(nums[i])

        return res
                

        