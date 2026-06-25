class Solution:
    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        n = len(nums)
        parent = [-1] * n
        tin = [0] * n
        tout = [0] * n
        subxor = [0] * n
        adj = [[] for _ in range(n)]
        timer = 0

        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        def dfs(u: int, par: int) -> None:
            nonlocal timer
            parent[u] = par
            tin[u] = timer
            timer += 1

            x = nums[u]
            for v in adj[u]:
                if v != par:
                    dfs(v, u)
                    x ^= subxor[v]

            subxor[u] = x
            tout[u] = timer

        dfs(0, -1)

        total = subxor[0]

        def is_ancestor(u: int, v: int) -> bool:
            return tin[u] <= tin[v] < tout[u]
        
        child = []
        for u, v in edges:
            if parent[u] == v:
                child.append(u)
            else:
                child.append(v)

        ans = float("inf")

        m = len(child)
        for i in range(m):
            a = child[i]
            for j in range(i + 1, m):
                b = child[j]
                if is_ancestor(a, b):
                    x = subxor[b]
                    y = subxor[a] ^ subxor[b]
                    z = total ^ subxor[a]
                elif is_ancestor(b, a):
                    x = subxor[a]
                    y = subxor[b] ^ subxor[a]
                    z = total ^ subxor[b]
                else:
                    x = subxor[a]
                    y = subxor[b]
                    z = total ^ subxor[a] ^ subxor[b]

                ans = min(ans, max(x, y, z) - min(x, y, z))

        return ans