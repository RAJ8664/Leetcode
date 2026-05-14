class Solution:
    def ok(self, mid, source, target, k, adj, n) -> bool:
        pq = []
        heapq.heappush(pq, source)
        dist = [(10 ** 9)] * (n + 1)
        dist[source] = 0
        
        while len(pq) > 0:
            curr_node = heapq.heappop(pq)
            for v, wt in adj[curr_node]:
                count = dist[curr_node]
                if wt > mid:
                    count += 1
                if dist[v] > count:
                    dist[v] = count
                    heapq.heappush(pq, v)

        if dist[target] == 10 ** 9 or dist[target] > k: return False
        return True

    def minimumThreshold(self, n: int, edges: List[List[int]], source: int, target: int, k: int) -> int:
        adj = [[] for _ in range(n + 1)]
        
        for edge in edges:
            u, v, wt = edge[0], edge[1], edge[2]
            adj[u].append((v, wt))
            adj[v].append((u, wt))

        low, high, ans = 0, 10 ** 9, -1
        while low <= high:
            mid = low + (high - low) // 2
            if self.ok(mid, source, target, k, adj, n):
                ans = mid
                high = mid - 1
            else:
                low = mid + 1
            
        return ans
        