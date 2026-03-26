import collections

class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        n = len(grid)
        m = len(grid[0])
        
        pref_row = [0] * n
        pref_col = [0] * m
        mp = collections.defaultdict(list)
        
        for i in range(n):
            row_sum = 0
            for j in range(m):
                val = grid[i][j]
                row_sum += val
                mp[val].append((i, j))
            pref_row[i] = row_sum + (pref_row[i-1] if i > 0 else 0)
            
        for j in range(m):
            col_sum = 0
            for i in range(n):
                col_sum += grid[i][j]
            pref_col[j] = col_sum + (pref_col[j-1] if j > 0 else 0)
            
        total = pref_row[-1]
        
        def can_remove(r1, c1, r2, c2, i, j):
            rows = r2 - r1 + 1
            cols = c2 - c1 + 1
            if rows * cols == 1: return False
            if rows == 1: return j == c1 or j == c2
            if cols == 1: return i == r1 or i == r2
            return True

        for i in range(n - 1):
            top = pref_row[i]
            bottom = total - top
            if top == bottom: return True
            
            diff = abs(top - bottom)
            if diff in mp:
                if top > bottom:
                    for x, y in mp[diff]:
                        if x <= i and can_remove(0, 0, i, m - 1, x, y):
                            return True
                else:
                    for x, y in mp[diff]:
                        if x > i and can_remove(i + 1, 0, n - 1, m - 1, x, y):
                            return True
                            
        for j in range(m - 1):
            left = pref_col[j]
            right = total - left
            if left == right: return True
            
            diff = abs(left - right)
            if diff in mp:
                if left > right:
                    for x, y in mp[diff]:
                        if y <= j and can_remove(0, 0, n - 1, j, x, y):
                            return True
                else:
                    for x, y in mp[diff]:
                        if y > j and can_remove(0, j + 1, n - 1, m - 1, x, y):
                            return True
                            
        return False