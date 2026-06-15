class Solution:
    def checkGoodInteger(self, n: int) -> bool:
        ds, ss = 0, 0
        
        while n > 0:
            dig = n % 10
            ds += dig
            ss += dig * dig
            n = n // 10

        return ss - ds >= 50
        