class Solution:
    def maximumPoints(self, edges: List[List[int]], coins: List[int], k: int) -> int:
        n = len(coins)
        adj = [[] for _ in range(n + 1)]

        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        dp = [[-1] * 15 for _ in range(n + 1)] 

        def dfs(u: int, par: int, times: int) -> None:
            if times >= 15:
                return 0 

            if dp[u][times] != -1:
                return dp[u][times]

            op1 = (coins[u] >>  times) - k
            op2 = (coins[u] >> (times + 1))

            for v in adj[u]:
                if v != par:
                    op1 += dfs(v, u, times)
                    op2 += dfs(v, u, times + 1)

            dp[u][times] = max(op1, op2)
            return dp[u][times]

        return dfs(0, -1, 0)
        