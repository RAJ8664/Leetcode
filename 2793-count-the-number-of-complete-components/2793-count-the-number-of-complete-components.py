class Solution:
    def dfs(self, u: int, vis, adj, temp) -> None:
        vis[u] = 1
        temp.append(u)
        for child in adj[u]:
            if vis[child] == 0:
                self.dfs(child, vis, adj, temp)

    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:
        adj = [[] for _ in range(n)]
    
        for edge in edges:
            u, v = edge[0], edge[1]
            adj[u].append(v)
            adj[v].append(u)

        vis = [0] * n
        count = 0
        
        for i in range(n):
            if vis[i] == 0:
                temp = []
                self.dfs(i, vis, adj, temp)
                if len(temp) == 1:
                    count += 1
                else:
                    flag = True
                    for ele in temp:
                        if len(adj[ele]) != len(temp) - 1:
                            flag = False
                            break

                    if flag:
                        count += 1

        return count