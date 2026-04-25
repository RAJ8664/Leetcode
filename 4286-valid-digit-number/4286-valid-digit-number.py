class Solution:
    def validDigit(self, n: int, x: int) -> bool:
        last, found = -1, 0
        while n > 0:
            if n % 10 == x:
                found = 1
            last = n % 10
            n = n // 10
        if last != x and found == 1:
            return True
        return  False
        