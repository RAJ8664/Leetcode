class Solution:
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        n = len(intervals)

        intervals.sort(key = lambda x: (x[0], -x[1]))

        count, right = 0, 0
        for start, end in intervals:
            if end > right:
                count += 1
            right = max(right, end)

        return count


        