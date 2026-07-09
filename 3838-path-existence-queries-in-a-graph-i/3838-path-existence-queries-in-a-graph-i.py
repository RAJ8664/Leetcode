class DSU:
    def __init__(self, n: int) -> None:
        self.parent = [0]  * (n + 1)
        self.size = [0] * (n + 1)

        for i in range(n):
            self.parent[i] = i
            self.size[i] = 1

    def get_parent(self, u: int) -> int:
        if self.parent[u] == u:
            return u
        self.parent[u] = self.get_parent(self.parent[u]) 
        return self.parent[u]
    
    def unite(self, u: int, v: int) -> None:
        u = self.get_parent(u)
        v = self.get_parent(v)

        if u == v:
            return

        if self.size[v] > self.size[u]:
            u, v = v, u

        self.parent[v] = u
        self.size[u] += self.size[v] 

class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[bool]:
        dsu = DSU(n) 

        left, right = 0, 0
        while left < n:
            if left == right:
                right += 1
            while right < n and abs(nums[left] - nums[right]) <= maxDiff:
                dsu.unite(left, right)
                right += 1
            left += 1

        ans = []
        for query in queries:
            u, v = query[0], query[1]
            if dsu.get_parent(u) == dsu.get_parent(v):
                ans.append(True)
            else:
                ans.append(False) 

        return ans