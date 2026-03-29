class DSU:
    def __init__(self, n):
        self.parent = list(range(n + 1))
        self.rank = [0] * (n + 1)
        self.state = [0] * (n + 1)

    def find(self, x):
        if self.parent[x] != x:
            root, st = self.find(self.parent[x])
            self.state[x] ^= st
            self.parent[x] = root
        return self.parent[x], self.state[x]

    def unite(self, u, v, need):
        if self.rank[u] < self.rank[v]:
            self.parent[u] = v
            self.state[u] = need
        elif self.rank[u] > self.rank[v]:
            self.parent[v] = u
            self.state[v] = need
        else:
            self.parent[v] = u
            self.state[v] = need
            self.rank[u] += 1


class Solution:
    def numberOfEdgesAdded(self, n, edges):
        dsu = DSU(n)
        total = 0

        for u, v, w in edges:
            pu, su = dsu.find(u)
            pv, sv = dsu.find(v)

            if pu != pv:
                dsu.unite(pu, pv, su ^ sv ^ w)
                total += 1
            else:
                if (su ^ sv ^ w) == 0:
                    total += 1

        return total