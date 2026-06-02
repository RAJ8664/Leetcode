class Solution:
    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        res = float('inf')
        n = len(landStartTime)
        m = len(waterStartTime)

        for i in range(n):
            for j in range(m):
                lf = landStartTime[i] + landDuration[i]
                f1 = max(lf, waterStartTime[j]) + waterDuration[j]
                wf = waterStartTime[j] + waterDuration[j]
                f2 = max(wf, landStartTime[i]) + landDuration[i]
                res = min(res, f1, f2)
        
        return res
        