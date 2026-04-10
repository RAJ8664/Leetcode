class Solution:
    def minimumDistance(self, nums: List[int]) -> int:
        n = len(nums)
        mini = (10 ** 9)
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    if nums[i] == nums[j] and nums[j] == nums[k] and nums[k] == nums[i]:
                        mini = min(mini, abs(i - j) + abs(j - k) + abs(k - i))

        if mini == (10 ** 9): return -1
        return mini
        