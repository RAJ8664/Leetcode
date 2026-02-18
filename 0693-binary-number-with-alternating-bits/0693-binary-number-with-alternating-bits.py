class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        prev = -1
        while n > 0:
            if prev == -1:
                prev = n % 2
                n >>= 1
                continue
            if n % 2 == prev:
                return False
            prev = n % 2
            n >>= 1
        return True          

        