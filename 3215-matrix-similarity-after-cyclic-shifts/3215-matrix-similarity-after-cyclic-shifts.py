class Solution:
    def areSimilar(self, mat: List[List[int]], k: int) -> bool:
        n, m = len(mat), len(mat[0])
        k %= m
        if not k:
            return True
        for i in range(n):
            row = mat[i][(-1) ** (i) * k:] + mat[i][:(-1) ** (i) * k]
            if mat[i] != row:
                return False
        return True