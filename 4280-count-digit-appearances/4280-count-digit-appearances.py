class Solution:
    def get_count(self, n: int, target: int) -> int:
        count = 0
        while n > 0: 
            if n % 10 == target: count += 1
            n = n // 10
        return count 

    def countDigitOccurrences(self, nums: list[int], digit: int) -> int:
        n = len(nums)
        res = 0
        for ele in nums:
            res += self.get_count(ele, digit)
        return res

        