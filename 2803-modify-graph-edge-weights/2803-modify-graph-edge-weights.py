class Solution:
    def modifiedGraphEdges(self, n: int, edges: List[List[int]], source: int, destination: int, target: int) -> List[List[int]]:
        def dj() -> int:
            adj = [[] for _ in range(n + 1)]

            for u, v, wt in edges:
                if wt != -1:
                    adj[u].append((v, wt))
                    adj[v].append((u, wt))

            dist = [10**9] * (n + 1)
            dist[source] = 0
            pq = []
            heapq.heappush(pq, (0, source))
            while pq:
                curr_weight, curr_node = heapq.heappop(pq)

                for child_node, child_weight in adj[curr_node]:
                    if dist[child_node] > curr_weight + child_weight:
                        dist[child_node] = curr_weight + child_weight
                        heapq.heappush(pq, (dist[child_node], child_node))
            
            return dist[destination]

        curr_shortest = dj()

        if curr_shortest < target:
            return []
        
        flag = False
        if curr_shortest == target:
            flag = True

        for edge in edges:
            if edge[2] > 0:
                continue
            edge[2] = 10 ** 9 if flag else 1
            if not flag:
                new_weight = dj()
                if new_weight <= target:
                    flag = True
                    edge[2] += target - new_weight
        
        if flag:
            return edges
        return []
