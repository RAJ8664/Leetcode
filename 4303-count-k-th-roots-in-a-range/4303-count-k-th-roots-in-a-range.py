class Solution:
    def countKthRoots(self, l: int, r: int, k: int) -> int:
        if k == 1: return r - l + 1
        count = 0
        idx = 0
        while True:
            power = pow(idx, k)
            if power >= l and power <= r:
                count += 1
            elif power > r:
                break
            idx += 1

        return count
            
