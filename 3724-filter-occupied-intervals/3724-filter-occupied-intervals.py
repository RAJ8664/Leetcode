class Solution:
    def filterOccupiedIntervals(self, occupiedIntervals: List[List[int]], freeStart: int, freeEnd: int) -> List[List[int]]:
        n = len(occupiedIntervals)
        res = []

        occupiedIntervals.sort(key = lambda x: (x[0], x[1])) 

        left, right = 0, 0
        while left < n:
            right = left
            curr_maxi = occupiedIntervals[left][1]
            while right < n and occupiedIntervals[right][0] <= curr_maxi + 1:
                curr_maxi = max(curr_maxi, occupiedIntervals[right][1])
                right += 1
            res.append((occupiedIntervals[left][0], curr_maxi))
            if left == right:
                left += 1
            else: left = right

        ans = []
        for curr_start, curr_end in res:
            if curr_start >= freeStart and curr_end <= freeEnd:
                continue

            elif curr_start > freeEnd or curr_end < freeStart:
                ans.append([curr_start, curr_end])
            
            elif freeStart > curr_start and freeEnd < curr_end:
                ans.append([curr_start, max(curr_start, freeStart - 1)])
                ans.append([freeEnd + 1, curr_end])

            elif curr_start <= freeStart and curr_end <= freeEnd:
                ans.append([curr_start, max(curr_start, freeStart - 1)]) 
            
            elif freeStart <= curr_start and freeEnd <= curr_end:
                ans.append([min(freeEnd + 1, curr_end), curr_end])

        return ans
        