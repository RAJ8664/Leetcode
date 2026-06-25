class Solution:
    def build_dp(self, u, par, adj, dp, depth) -> None:
        dp[u][0] = par
        
        for i in range(1, 19):
            dp[u][i] = dp[dp[u][i - 1]][i - 1] 

        for child in adj[u]:
            if child != par:
                depth[child] = 1 + depth[u]
                self.build_dp(child, u, adj, dp, depth)

    def find_kth_parent(self, u, k, dp) -> None:
        count = 0
        
        while k > 0:
            if k % 2 == 1:
                u = dp[u][count]
            count += 1
            k = k // 2
        
        return u

    def dfs(self, u, par, adj, dp, depth, pos, nums, level, ans):
        pos[nums[u]].append(level)

        for child in adj[u]:
            if child != par:
                curr_val = nums[child]
                flag = False
                mini_pos = 10**9
                for i in range(1, 51):
                    if math.gcd(curr_val, i) == 1:
                        positions = pos[i]
                        if len(positions) == 0: continue
                        flag = True
                        best_pos = (level + 1) - positions[len(positions) - 1]          
                        mini_pos = min(mini_pos, best_pos)

                if flag:
                    res = self.find_kth_parent(child, mini_pos, dp)
                    ans[child] = res
                else:
                    ans[child] = -1

                self.dfs(child, u, adj, dp, depth, pos, nums, level + 1, ans)
                    
        pos[nums[u]].pop(len(pos[nums[u]]) - 1)

    def getCoprimes(self, nums: List[int], edges: List[List[int]]) -> List[int]:
        nums = [0] + nums
        n = len(nums)
        adj = [[] for _ in range(n + 1)]
        dp = [[0] * 19 for _ in range(n + 1)]
        depth = [0] * (n + 1)
        pos = [[] for _ in range(51)]
        ans = [-1] * n

        for edge in edges:
            u, v = edge[0] + 1, edge[1] + 1
            adj[u].append(v)
            adj[v].append(u)

        self.build_dp(1, -1, adj, dp, depth)

        self.dfs(1, -1, adj, dp, depth, pos, nums, 0, ans)

        ans.pop(0)
        for i in range(len(ans)):
            if ans[i] == -1: continue
            ans[i] -= 1
            
        return ans