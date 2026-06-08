class Solution:
    def minEnergy(self, n: int, brightness: int, intervals: list[list[int]]) -> int:
        intervals.sort()
        res = []
        for start, end in intervals:
            if res and start <= res[-1][1] + 1:
                res[-1][1] = max(res[-1][1], end)
            else:
                res.append([start, end])
        total = 0
        for start, end in res:
            total += end - start + 1
        return total * math.ceil(brightness / 3)


        