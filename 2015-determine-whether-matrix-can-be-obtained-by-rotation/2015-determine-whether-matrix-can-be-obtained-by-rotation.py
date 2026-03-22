class Solution:
    def isSame(self, arr, brr) -> bool: 
        n = len(arr)
        for i in range(n): 
            for j in range(n): 
                if arr[i][j] != brr[i][j]: return False
        
        return True


    def Rotate(self, mat):
        n = len(mat)
        res = [[0] * n for _ in range(n)]
        col = n - 1
        for i in range(n):
            temp = list(mat[i])
            idx = 0
            for x in range(n):
                res[x][col] = temp[idx]
                idx += 1 
            col -= 1 

        return res 

    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        n = len(mat)
        count = 0
        while (count < 4): 
            if (self.isSame(mat, target)): return True
            mat = self.Rotate(mat)
            count += 1
        return False
         