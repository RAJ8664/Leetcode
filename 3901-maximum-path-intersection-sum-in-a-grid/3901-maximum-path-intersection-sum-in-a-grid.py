class Solution:
    def maxScore(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])

        def kadane(arr) -> int:
            n = len(arr)
            if n < 2:
                return 0
            curr = arr[0] + arr[1]
            maxi = curr
            for i in range(2, n):
                curr = max(curr + arr[i], arr[i - 1] + arr[i])
                maxi = max(maxi, curr)
        
            return maxi

        res = -10 ** 9
        for i in range(n):
            res = max(res, kadane(grid[i]))

        for j in range(m):
            arr = []
            for i in range(n):
                arr.append(grid[i][j]) 
            res = max(res, kadane(arr))

        for i in range(1, n - 1):
            for j in range(1, m - 1):
                res = max(res, grid[i][j])
                
        return res