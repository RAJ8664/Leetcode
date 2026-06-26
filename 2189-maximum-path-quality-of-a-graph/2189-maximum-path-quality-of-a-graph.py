class Solution:
    def maximalPathQuality(self, vals, edges, maxTime):
        n = len(vals)
        adj = [[] for _ in range(n)]
        for u, v, t in edges:
            adj[u].append((v, t))
            adj[v].append((u, t))

        vis = [0] * n
        self.max_val = 0

        def dfs(i: int, time: int, val: int):
            vis[i] += 1
            if vis[i] == 1:
                val += vals[i]

            if i == 0:
                self.max_val = max(self.max_val, val)

            for j, t in adj[i]:
                if time - t >= 0:
                    dfs(j, time - t, val)

            vis[i] -= 1

        dfs(0, maxTime, 0)
        return self.max_val