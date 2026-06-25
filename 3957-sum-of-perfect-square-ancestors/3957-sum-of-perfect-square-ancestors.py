class Solution:
    def get_factors(self, n: int):
        res = []
        cnt = 0
        while n % 2 == 0:
            cnt += 1
            n //= 2
        if cnt & 1:
            res.append(2)
        p = 3
        while p * p <= n:
            cnt = 0
            while n % p == 0:
                cnt += 1
                n //= p
            if cnt & 1:
                res.append(p)
            p += 2
        if n > 1:
            res.append(n)

        return tuple(res)

    def sumOfAncestors(self, n: int, edges: List[List[int]], nums: List[int]) -> int:
        adj = [[] for _ in range(n)]

        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        freq = defaultdict(int)
        ans = 0

        def dfs(u: int, par: int):
            nonlocal ans
            ft = self.get_factors(nums[u])
            ans += freq[ft]
            freq[ft] += 1
            for v in adj[u]:
                if v != par:
                    dfs(v, u)

            freq[ft] -= 1

        dfs(0, -1)

        return ans