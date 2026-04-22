import heapq

class Tuple:
    def __init__(self, row, col, time):
        self.row = row
        self.col = col
        self.time = time
    
    def __repr__(self):
        return f"({self.row}, {self.col}, {self.time})"

    def __lt__(self, other):
        return self.time < other.time

class Solution:
    def colorGrid(self, n: int, m: int, sources: list[list[int]]) -> list[list[int]]:
        time = [[10 ** 9] * m for _ in range(n)]
        color = [[0] * m for _ in range(n)]
        vis = [[0] * m for _ in range(n)]

        pq = []
        for i in range(len(sources)):
            curr_row, curr_col, curr_color = sources[i][0], sources[i][1], sources[i][2]
            heapq.heappush(pq, Tuple(curr_row, curr_col, 0))
            color[curr_row][curr_col] = curr_color 
            time[curr_row][curr_col] = 0
            vis[curr_row][curr_col] = 1     

        dir = [[-1, 0], [1, 0], [0, -1], [0, 1]]        
        while pq:
            curr_tuple = heappop(pq)
            curr_row, curr_col, curr_time = curr_tuple.row, curr_tuple.col, curr_tuple.time
            for dire in dir:
                new_row, new_col, new_time = curr_row + dire[0], curr_col + dire[1], curr_time + 1
                if new_row >= 0 and new_row < n and new_col >= 0 and new_col < m:
                    if vis[new_row][new_col] == 1:
                        if time[new_row][new_col] == new_time:
                            if color[new_row][new_col] < color[curr_row][curr_col]:
                                color[new_row][new_col] = color[curr_row][curr_col]
                                heapq.heappush(pq, Tuple(new_row, new_col, new_time))
                    else:
                        vis[new_row][new_col] = 1
                        time[new_row][new_col] = curr_time + 1
                        color[new_row][new_col] = color[curr_row][curr_col]
                        heapq.heappush(pq, Tuple(new_row, new_col, time[new_row][new_col]))

        return color
