class Solution:
    def evenSumSubgraphs(self, nums: list[int], edges: list[list[int]]) -> int:
        n = len(nums)
        combinations = []

        def solve(ind: int, current: list[int], n: int) -> None:
            if ind == n:
                if len(current):
                    combinations.append(list(current))
                return

            current.append(ind)
            solve(ind + 1, current, n)
            current.pop()
            solve(ind + 1, current, n)

        solve(0, [], n)

        count = 0
        for comb in combinations:

            def ok(comb: list[int]) -> bool:
                sum = 0
                for ele in comb:
                    sum += nums[ele]
                if sum % 2 == 1:
                    return False

                # Check if the subgrah is connected ?

                # Map the nodes to the zero based indexing and create the adjacency list
                mp = dict()
                for i in range(len(comb)):
                    mp[comb[i]] = i

                adj = [[] for _ in range(len(comb) + 1)]
                vis = [0] * (len(comb) + 1)
                for edge in edges:
                    u, v = edge[0], edge[1]
                    if u in comb and v in comb:
                        adj[mp[u]].append(mp[v])
                        adj[mp[v]].append(mp[u])

                def dfs(node: int, par: int) -> None:
                    vis[node] = 1
                    for v in adj[node]:
                        if vis[v] == 0:
                            dfs(v, node)

                dfs(0, -1)

                for i in range(len(comb)):
                    if vis[i] == 0:
                        return False
                return True

            if ok(comb):
                count += 1

        return count

