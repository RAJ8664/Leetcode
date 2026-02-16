class Solution:
    def reverseBits(self, n: int) -> int:
        if n == 0: 
            return 0
        res = 0
        for i in range(0, 32): 
            res = res << 1
            if ((n & 1) == 1): 
                res += 1
            n >>= 1
        return res
        