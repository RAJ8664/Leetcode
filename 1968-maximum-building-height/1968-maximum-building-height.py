class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        arr = [[1, 0]]
        arr.extend(restrictions)
        arr.sort()

        for i in range(len(arr) - 2, -1, -1):
            diff = arr[i + 1][0] - arr[i][0]
            arr[i][1] = min(arr[i][1], arr[i + 1][1] + diff)

        for i in range(1, len(arr)):
            diff = arr[i][0] - arr[i - 1][0]
            arr[i][1] = min(arr[i][1], arr[i - 1][1] + diff)

        res = 0
        for i in range(1, len(arr)):
            a, b = arr[i - 1]
            c, d = arr[i]
            diff = c - a
            res = max(res, (b + d + diff) // 2)


        x, y = arr[-1]
        res = max(res, y + (n - x))

        return res