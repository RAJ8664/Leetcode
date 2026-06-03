class Solution:
    def solve(self, ls, ld, ws, wd):
        res = float('inf')
        mini = float('inf')

        for i in range(len(ls)):
            mini = min(mini, ls[i] + ld[i])

        for i in range(len(ws)):
            f = max(mini, ws[i]) + wd[i]
            res = min(res, f)

        return res

    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        
        op1 = self.solve(landStartTime, landDuration, waterStartTime, waterDuration)
        op2 = self.solve(waterStartTime, waterDuration, landStartTime, landDuration)
        return min(op1, op2) 
        