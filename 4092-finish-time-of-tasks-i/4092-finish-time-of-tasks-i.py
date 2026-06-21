class Solution:
    def finishTime(self, n: int, edges: List[List[int]], baseTime: List[int]) -> int:
        adj = [[] for _ in range(n + 1)]
        final_time = [0] * (n + 1)

        for edge in edges:
            adj[edge[0]].append(edge[1])
            adj[edge[1]].append(edge[0])

        if n == 1: 
            return baseTime[0]
        
        def dfs(u: int, par: int, base_time: List[int]) -> None:
            if len(adj[u]) == 1 and u != 0:
                final_time[u] = base_time[u]
                return

            mini = float('inf')
            maxi = -float('inf')
            
            for child in adj[u]:
                if child != par: 
                    dfs(child, u, base_time)
                    mini = min(mini, final_time[child])
                    maxi = max(maxi, final_time[child])
                     
            final_time[u] = base_time[u] + 2 * maxi - mini 
        
        dfs(0, -1, baseTime)

        return final_time[0] 