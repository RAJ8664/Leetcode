class Solution:
    def dj(self, s: int, n: int, adj: List[List[tuple]]) -> List[int]:
        INF = float("inf")
        dist = [INF] * n
        dist[s] = 0
        pq = [(0, s)]
        while pq:
            curr_dist, u = heapq.heappop(pq)
            if curr_dist > dist[u]:
                continue
            for v, w in adj[u]:
                if dist[v] > curr_dist + w:
                    dist[v] = curr_dist + w
                    heapq.heappush(pq, (dist[v], v))

        return dist

    def findAnswer(self, n: int, edges: List[List[int]]) -> List[bool]:
        adj = [[] for _ in range(n)]

        for u, v, w in edges:
            adj[u].append((v, w))
            adj[v].append((u, w))

        d1 = self.dj(0, n, adj)
        d2 = self.dj(n - 1, n, adj)

        shortest_dist = d1[n - 1]
        ans = []
        for u, v, w in edges:
            ok = False
            if d1[u] != float("inf") and d2[v] != float("inf") and d1[u] + w + d2[v] == shortest_dist:
                ok = True
            if d1[v] != float("inf") and d2[u] != float("inf") and d1[v] + w + d2[u] == shortest_dist:
                ok = True

            ans.append(ok)

        return ans