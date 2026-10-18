class Solution:
    def get_id(self, color):
        if color == 'R': return 0
        elif color == 'B': return 1
        return 2
        
    def findColoredRoot(self, edges: list[list[int]], colors: str) -> int:
        n = len(colors)

        indegree = [0] * (n + 1)
        degree = [0] * (n + 1)

        for edge in edges:
            u, v = edge[0], edge[1]
            degree[u] += 1
            degree[v] += 1

            if self.get_id(colors[u]) == self.get_id(colors[v]):
                return -1

            col_x, col_y = self.get_id(colors[u]), self.get_id(colors[v])

            if col_x == (col_y + 1) % 3:
                indegree[u] += 1
            else:
                indegree[v] += 1

        total = 0 
        res = []
        for i in range(n):
            if indegree[i] == 0:
                total += 1
                res.append(i)

        if total > 1:
            return -1
        
        if degree[res[0]] <= 2:
            return res[0]
       
        return -1
        
        