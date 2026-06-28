class Pair:
    def __init__(self, start: int, end: int) -> None:
        self.start = start
        self.end = end

    def __repr__(self) -> str:
        return f"({self.start}, {self.end})"

    def __lt__(self, other):
        return self.start <= other.start
        
class Solution:
    def filterOccupiedIntervals(self, occupiedIntervals: List[List[int]], freeStart: int, freeEnd: int) -> List[List[int]]:
        n = len(occupiedIntervals)
        arr = []
        res = []

        for i in range(n):
            arr.append(Pair(occupiedIntervals[i][0], occupiedIntervals[i][1]))

        arr.sort()

        print(arr)

        left, right = 0, 0
        while left < n:
            right = left
            curr_maxi = arr[left].end
            while right < n and arr[right].start <= curr_maxi + 1:
                curr_maxi = max(curr_maxi, arr[right].end)
                right += 1
            res.append((arr[left].start, curr_maxi))
            if left == right:
                left += 1
            else: left = right

        print(res) 
        
        ans = []
        for curr_start, curr_end in res:
            if curr_start > freeEnd or curr_end < freeStart:
                ans.append([curr_start, curr_end])
            
            elif curr_start >= freeStart and curr_end <= freeEnd:
                continue

            elif freeStart > curr_start and freeEnd < curr_end:
                ans.append([curr_start, max(curr_start, freeStart - 1)])
                ans.append([freeEnd + 1, curr_end])

            elif curr_start <= freeStart and curr_end <= freeEnd:
                ans.append([curr_start, max(curr_start, freeStart - 1)]) 
            
            elif freeStart <= curr_start and freeEnd <= curr_end:
                ans.append([min(freeEnd + 1, curr_end), curr_end])

        return ans
        