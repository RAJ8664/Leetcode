class Solution:
    class Edge:
        def __init__(self, to: int, cost: int) -> None:
            self.to = to
            self.cost = cost
        
        def __repr__(self) -> str:
            return f"({self.to}, {self.cost})"

    def findMaxPathScore(self, edges, online, k):
        n = len(online)
        adj = [[] for _ in range(n)]
        unique_weights = set()
        indegree = [0] * n
        
        for u, v, w in edges:
            adj[u].append(self.Edge(v, w))
            indegree[v] += 1
            unique_weights.add(w)

        topo = []
        q = deque()
        for i in range(n):
            if indegree[i] == 0:
                q.append(i)

        while q:
            node = q.popleft()
            topo.append(node)
            for e in adj[node]:
                indegree[e.to] -= 1
                if indegree[e.to] == 0:
                    q.append(e.to)

        weights = sorted(unique_weights)

        low, high = 0, len(weights) - 1
        ans = -1
        while low <= high:
            mid = (low + high) // 2
            min_edge = weights[mid]
            if self.canReach(min_edge, online, k, n, adj, topo):
                ans = mid
                low = mid + 1
            else:
                high = mid - 1

        return -1 if ans == -1 else weights[ans]

    def canReach(self, minEdge, online, k, n, adj, topo):
        INF = sys.maxsize
        cost = [INF] * n
        cost[0] = 0
        for u in topo:
            if cost[u] > k:
                continue
            if not online[u] and u != 0 and u != n - 1:
                continue
            for e in adj[u]:
                v = e.to
                w = e.cost
                if w < minEdge:
                    continue
                if not online[v] and v != n - 1:
                    continue
                if cost[u] + w < cost[v]:
                    cost[v] = cost[u] + w

        return cost[n - 1] <= k
        