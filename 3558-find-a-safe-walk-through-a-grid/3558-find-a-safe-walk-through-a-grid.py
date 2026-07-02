class Solution:
    def findSafeWalk(self, grid: List[List[int]], health: int) -> bool:
        n, m = len(grid), len(grid[0])

        dist = [[10**9 for _ in range(m)] for _ in range(n)]
        dir = [[-1, 0], [1, 0], [0, 1], [0, -1]]

        pq = []
        pq.append((grid[0][0], 0, 0))
        dist[0][0] = grid[0][0]

        while pq:
            curr_cost, curr_row, curr_col = pq.pop()

            for dire in dir:
                new_row, new_col = curr_row + dire[0], curr_col + dire[1]
                
                if new_row < n and new_col < m and new_row >= 0 and new_col >= 0:
                    if dist[new_row][new_col] > curr_cost + grid[new_row][new_col]:
                        dist[new_row][new_col] = curr_cost + grid[new_row][new_col]
                        pq.append((dist[new_row][new_col], new_row, new_col))


        if health - dist[n - 1][m - 1] >= 1:
            return True 
        return False 
        