class Solution:
    def ok(self, m: int, n: int, k: int, current: int) -> bool:
        count = 0
        for i in range(1, m + 1):
            x = min(current // i, n)
            if x == 0: 
                break
            count += x
        return count >= k
    
    def findKthNumber(self, m: int, n: int, k: int) -> int:
        if m > n: 
            m, n = n, m
        low, high = 1, m * n
        while low < high: 
            mid = low + (high - low) // 2
            if  self.ok(m, n, k, mid): high = mid
            else: low = mid + 1
        return low

    
        