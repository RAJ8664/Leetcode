class Solution:
    def minTimeMaxPower(self, n: int, edges: List[List[int]], power: int, cost: List[int], source: int, target: int) -> List[int]:
        adj = [[] for _ in range(n)]
        INF = 10**18
        dist = [[INF] * (power + 1) for _ in range(n)]

        for u, v, t in edges:
            adj[u].append((v, t))

        dist[source][power] = 0
        pq = [(0, source, power)]
        while pq:
            curr_time, u, curr_power = heapq.heappop(pq)

            if curr_time != dist[u][curr_power]:
                continue

            if curr_power < cost[u]:
                continue

            new_power = curr_power - cost[u]
            for v, wt in adj[u]:
                new_time = curr_time + wt
                if new_time < dist[v][new_power]:
                    dist[v][new_power] = new_time
                    heapq.heappush(pq, (new_time, v, new_power))

        shortest_time = min(dist[target])

        if shortest_time == INF:
            return [-1, -1]

        max_power = -1
        for p in range(power + 1):
            if dist[target][p] == shortest_time:
                max_power = max(max_power, p)

        return [shortest_time, max_power]