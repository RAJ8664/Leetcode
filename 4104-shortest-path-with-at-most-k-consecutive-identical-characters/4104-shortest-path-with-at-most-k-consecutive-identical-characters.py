class Data:
    def __init__(self, node: int, curr_weight: int, last_char: str, repeat: int) -> None:
        self.node = node
        self.curr_weight = curr_weight
        self.last_char = last_char
        self.repeat = repeat

    def __repr__(self) -> str:
        return f"({self.node}, {self.curr_weight}, {self.last_char}, {self.repeat})"

    def __lt__(self, other):
        return self.curr_weight <= other.curr_weight

class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        adj = [[] for _ in range(n + 1)]

        for edge in edges:
            u, v, wt = edge[0], edge[1], edge[2]
            adj[u].append((v, wt))

        if n == 1:
            return 0

        pq = []
        dist = [10 ** 9] * (n + 1)
        heapq.heappush(pq, Data(0, 0, str(labels[0]), 1))
        
        while pq:
            curr_data = heapq.heappop(pq)
            for child_node, child_weight in adj[curr_data.node]:
                child_label = str(labels[child_node])
                
                # check if this can be a valid path
                if child_label == curr_data.last_char:
                    if curr_data.repeat + 1 <= k:
                        if dist[child_node] > curr_data.curr_weight + child_weight:
                            dist[child_node] = curr_data.curr_weight + child_weight 
                            heapq.heappush(pq, Data(child_node, dist[child_node], child_label, curr_data.repeat + 1))
                else:
                    if dist[child_node] > curr_data.curr_weight + child_weight:
                        dist[child_node] = curr_data.curr_weight + child_weight 
                        heapq.heappush(pq, Data(child_node, dist[child_node], child_label, 1))

        if dist[n - 1] == 10 ** 9: return -1
        return dist[n - 1]        