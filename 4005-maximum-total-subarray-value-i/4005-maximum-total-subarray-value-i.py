class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        maxi = -float('inf')
        mini = float('inf')
        for ele in nums:
            maxi = max(maxi, ele)
            mini = min(mini, ele)
        return k * (maxi - mini)
