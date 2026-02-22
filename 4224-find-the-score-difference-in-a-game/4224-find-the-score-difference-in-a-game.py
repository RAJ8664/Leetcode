class Solution:
    def scoreDifference(self, nums: List[int]) -> int:
        n = len(nums)
        count = [0] * 2
        current = 0
        for game, points in enumerate(nums, 1): 
            current ^= (points % 2) ^ (game % 6 == 0)
            count[current] += points
        return count[0] - count[1]
        