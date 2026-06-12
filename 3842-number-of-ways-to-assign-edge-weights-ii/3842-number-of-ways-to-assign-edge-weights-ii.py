class Solution:
    MOD = 10**9 + 7
    LOG = 17 + 1

    def assignEdgeWeights(self, edges, queries):
        n = len(edges) + 1
        adj = [[] for _ in range(n + 1)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        depth = [0] * (n + 1)
        up = [[0] * self.LOG for _ in range(n + 1)]

        stack = [(1, 0)]
        order = []
        while stack:
            u, p = stack.pop()
            up[u][0] = p
            order.append((u, p))

            for v in adj[u]:
                if v != p:
                    depth[v] = depth[u] + 1
                    stack.append((v, u))

        for j in range(1, self.LOG):
            for i in range(1, n + 1):
                up[i][j] = up[up[i][j - 1]][j - 1]

        def lca(u, v):
            if depth[u] < depth[v]:
                u, v = v, u
            diff = depth[u] - depth[v]
            bit = 0
            while diff:
                if diff & 1:
                    u = up[u][bit]
                diff >>= 1
                bit += 1
            if u == v:
                return u
            for j in range(self.LOG - 1, -1, -1):
                if up[u][j] != up[v][j]:
                    u = up[u][j]
                    v = up[v][j]

            return up[u][0]

        ans = []
        for u, v in queries:
            w = lca(u, v)
            dist = depth[u] + depth[v] - 2 * depth[w]
            if dist == 0:
                ans.append(0)
            else:
                ans.append(pow(2, dist - 1, self.MOD))

        return ans