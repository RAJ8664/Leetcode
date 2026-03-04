class Solution:
    def minimumOR(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])
        ans = (1 << 18) - 1
        for i in range(17, -1, -1):
            x = ans ^ (1 << i)
            flag = True
            for j in grid:
                zero = False
                for k in j:
                    if (k | x) == x:
                        zero = True
                        break
                if not zero:
                    flag = False
                    break
            if flag:
                ans = x
        return ans