class Solution:
    def maxRatings(self, units: List[List[int]]) -> int:
        # find smallest contributor box and dump all the smallest elements from the boxes to that smallest contributor box
        # Let's do this

        n, m = len(units), len(units[0])
        mini, min_val = 0, float('inf') 
        
        if m == 1:
            res = 0
            for i in range(n):
                res += units[i][0]
            return res

        for i in range(n):
            units[i].sort()
            if units[i][1] < units[mini][1]:
                mini = i
            min_val = min(min_val, units[i][0])

        res = min_val
        for i in range(n):
            if i == mini:
                continue
            else:
                res += units[i][1]

        return res
