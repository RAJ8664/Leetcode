class Solution:
    def jump(self, u: int, v: int, dp) -> int:
        if u == v:
            return 0

        if dp[u][0] >= v: # In one move i can go upper than this node
            return 1

        if dp[u][18] < v: # Even if i try the farthest possible jump, i cannot jump higher or to node v (meaning u and v are in diff components)
            return -1

        count = 0
        for i in range(18, -1, -1):
            if dp[u][i] < v:
                # This is the best we can jump as of now.
                count += (1 << i)
                u = dp[u][i]

        return count + 1 
        
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[int]:
        dp = [[0 for _ in range(19)] for _ in range(n + 1)]
        order = []
        pos = [0] * n
        
        for i in range(n):
            order.append((nums[i], i))

        order.sort()
        
        for i in range(n):
            pos[order[i][1]] = i

        left, right = 0, 0
        while left < n:
            while right + 1 < n and order[right + 1][0] - order[left][0] <= maxDiff:
                right += 1
            
            dp[left][0] = right
            left += 1

        for j in range(1, 19):
            for i in range(n):
                dp[i][j] = dp[dp[i][j - 1]][j - 1]

        ans = [] 
        for query in queries:
            u, v = min(pos[query[0]], pos[query[1]]), max(pos[query[0]], pos[query[1]])
            ans.append(self.jump(u, v, dp))
        
        return ans
