class Solution:
    def minimumCost(self, start, target, roads):
        ans = self.dist(start[0], start[1], target[0], target[1])
        n = len(roads)
        dp = [float('inf')] * (n + 1)
        q = deque()
        q.append((n, 0))
        while q:
            u, cost = q.popleft()
            if cost <= dp[u]:
                x = start[0] if u == n else roads[u][2]
                y = start[1] if u == n else roads[u][3]
                for v in range(n):
                    sx, sy, ex, ey, w = roads[v]
                    dp[v] = min(dp[v], cost + self.dist(x, y, ex, ey))
                    nc = cost + w + self.dist(x, y, sx, sy)
                    if nc < dp[v]:
                        dp[v] = nc
                        q.append((v, nc))
                        ans = min(ans, nc + self.dist(ex, ey, target[0], target[1]))
        return ans

    def dist(self, x1, y1, x2, y2):
        return abs(x2 - x1) + abs(y2 - y1)