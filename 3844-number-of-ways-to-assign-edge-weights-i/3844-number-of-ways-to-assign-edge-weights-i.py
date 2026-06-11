class Solution:
    def assignEdgeWeights(self, edges):
        n = len(edges) + 1
        adj = [[] for _ in range(n + 1)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
        max_depth = 0
        stack = [(1, 0, 0)]
        while stack:
            u, par, depth = stack.pop()
            max_depth = max(max_depth, depth)

            for v in adj[u]:
                if v != par:
                    stack.append((v, u, depth + 1))
        MOD = 10**9 + 7
        if max_depth == 0:
            return 0
        return pow(2, max_depth - 1, MOD)