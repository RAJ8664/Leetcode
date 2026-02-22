class Solution:
    def binaryGap(self, n: int) -> int:
        temp = n
        res = []
        while temp > 0: 
            res.append((temp & 1))
            temp >>= 1
        res.reverse()
        maxi = 0
        prev = -1
        for i in range(len(res)): 
            if res[i] == 1: 
                if prev != -1:
                    maxi = max(maxi, i - prev)
                    prev = i
                else:
                    prev = i
        return maxi
        