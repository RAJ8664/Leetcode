class Solution:
    def createGrid(self, m: int, n: int) -> list[str]:
        m, n = n, m
        res = []
        for i in range(n):
            curr_str = ""
            for j in range(m):
                if i == 0 or j == m - 1:
                    curr_str += "."
                else:
                    curr_str += "#"
            print(curr_str)
            res.append(curr_str)

        return list(res)