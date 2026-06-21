class Solution:
    def maxDistance(self, moves: str) -> int:
        u, d, l, r = 0, 0, 0, 0
        count = 0
        
        for ch in moves:
            if ch == 'L':
                l += 1
            elif ch == 'R':
                r += 1
            elif ch == 'U':
                u +=  1
            elif ch == 'D':
                d += 1
            else:
                count += 1

        count += abs(l - r) + abs(u - d) 
        return count
        