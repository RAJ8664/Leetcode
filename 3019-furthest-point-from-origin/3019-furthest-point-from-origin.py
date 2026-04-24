class Solution:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        n = len(moves)
        left, right, extra = 0, 0, 0
        for i in range(n):
            if moves[i] == 'L': left += 1
            elif moves[i] == 'R': right += 1
            else: extra += 1
        
        return abs(left - right) + extra
        