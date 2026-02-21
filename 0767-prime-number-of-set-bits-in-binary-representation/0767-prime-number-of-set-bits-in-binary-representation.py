class Solution:
    
    def is_prime(self, n: int) -> bool: 
        if n <= 1:
            return False
        i = 2
        while i * i <= n:
            if n % i == 0:
                return False
            i += 1
        return True
    
    def count(self, n: int) -> int: 
        temp = n
        res = 0
        while temp > 0: 
            res += 1
            temp = (temp & (temp - 1))
        return res

    def countPrimeSetBits(self, left: int, right: int) -> int:
        res = 0
        for i in range(left, right + 1): 
            if self.is_prime(self.count(i)):
                res += 1
        return res
        