class Solution:
    def dominantIndices(self, nums: List[int]) -> int:
        n = len(nums)
        total_sum, count = 0, 0
        for ele in nums:
            total_sum += ele
        for i in range(0, n):
            if i == n - 1:
                continue
            total_sum -= nums[i]
            if total_sum / (n - i - 1) < nums[i]:
                count += 1
        return count

