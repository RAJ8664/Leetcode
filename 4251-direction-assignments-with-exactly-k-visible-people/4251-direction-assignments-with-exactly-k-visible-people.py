class Solution:
    MOD = 10 ** 9 + 7
    def countVisiblePeople(self, n: int, pos: int, k: int) -> int:
        if k > n - 1: return 0
        m = n - 1
        r, num, den = min(k, m - k), 1, 1
        for i in range(r): 
            num = (num * (m - i)) % self.MOD
            den = (den * (i + 1)) % self.MOD
        res = (num * pow(den, self.MOD - 2, self.MOD)) % self.MOD
        return (2 * res) % self.MOD
        


        
        