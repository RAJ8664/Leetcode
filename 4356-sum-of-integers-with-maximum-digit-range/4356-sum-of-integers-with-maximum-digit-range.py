class Solution:
    def get_digit_range(self, num: int) -> int:
        maxi, mini = -1, 10
        while num > 0:
            maxi = max(maxi, num % 10)
            mini = min(mini, num % 10)
            num = num // 10
            
        return maxi - mini

    def maxDigitRange(self, nums: list[int]) -> int:
        n = len(nums)

        digit_range = [0] * n

        for i in range(n):
            digit_range[i] = self.get_digit_range(nums[i])

        maxi = 0
        for ele in digit_range:
            maxi = max(maxi, ele)

        res = 0
        for i in range(n):
            if digit_range[i] == maxi:
                res += nums[i]

        return res 