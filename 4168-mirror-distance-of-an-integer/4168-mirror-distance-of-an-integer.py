class Solution:
    def get_reverse(self, n: int) -> int:
        res = 0
        while n:
            res = res * 10 + n % 10
            n = n // 10
        return res

    def mirrorDistance(self, n: int) -> int:
        return abs(n - self.get_reverse(n))
        