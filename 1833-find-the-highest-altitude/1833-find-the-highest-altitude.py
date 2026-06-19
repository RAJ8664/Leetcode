class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        res = -float('inf')
        sum = 0
        for ele in gain:
            sum += ele
            res = max(res, sum)
        return max(0, res)