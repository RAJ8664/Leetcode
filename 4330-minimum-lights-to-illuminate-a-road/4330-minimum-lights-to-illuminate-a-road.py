class Solution:
    def minLights(self, arr: list[int]) -> int:
        n = len(arr)
        res = [0] * (n + 1)

        for i in range(n):
            if arr[i] > 0:
                left = max(0, i - arr[i])
                right = min(n - 1, i + arr[i])
                res[left] += 1
                res[right + 1] -= 1

        vis = [0] * (n + 1)
        sum = 0
        for i in range(n):
            sum += res[i]
            if sum > 0:
                vis[i] = 1

        count = 0
        for i in range(n):
            if vis[i] == 1:
                continue
            
            count += 1
            next = i + 1
            next = min(i + 1, n - 1)
            for j in range(max(0, next - 1), min(n - 1, next + 1) + 1):
                vis[j] = 1

        return count
 