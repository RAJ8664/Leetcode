class Solution:
    dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def maximumSafenessFactor(self, grid):
        n, m = len(grid), len(grid[0])

        self.min = [[10**9] * m for _ in range(n)]

        q = deque()
        for i in range(n):
            for j in range(m):
                if grid[i][j] == 1:
                    q.append((i, j, i, j))   # row, col, parent_row, parent_col
                    self.min[i][j] = 0

        while q:
            curr_row, curr_col, p_row, p_col = q.popleft()
            for dr, dc in self.dir:
                new_row = curr_row + dr
                new_col = curr_col + dc
                if 0 <= new_row < n and 0 <= new_col < m:
                    new_dist = abs(p_row - new_row) + abs(p_col - new_col)

                    if self.min[new_row][new_col] > new_dist:
                        self.min[new_row][new_col] = new_dist
                        q.append((new_row, new_col, p_row, p_col))

        low, high = 0, 10**9
        ans = -1
        while low <= high:
            mid = (low + high) // 2
            if self.ok(mid, grid):
                ans = mid
                low = mid + 1
            else:
                high = mid - 1

        return ans

    def ok(self, target, grid):
        n, m = len(grid), len(grid[0])

        if self.min[0][0] < target:
            return False

        dist = [[-10**9] * m for _ in range(n)]
        dist[0][0] = self.min[0][0]

        pq = [(-self.min[0][0], 0, 0)]
        while pq:
            neg_cost, curr_row, curr_col = heapq.heappop(pq)
            curr_cost = -neg_cost
            if curr_row == n - 1 and curr_col == m - 1:
                return True
            for dr, dc in self.dir:
                new_row = curr_row + dr
                new_col = curr_col + dc
                if 0 <= new_row < n and 0 <= new_col < m:
                    new_cost = min(self.min[new_row][new_col], curr_cost)
                    if dist[new_row][new_col] < new_cost:
                        if new_cost >= target:
                            dist[new_row][new_col] = new_cost
                            heapq.heappush(pq, (-new_cost, new_row, new_col))

        return False