class Solution:
    def canEat(self, candiesCount: List[int], queries: List[List[int]]) -> List[bool]:
        n = len(candiesCount)
        res = [0] * len(queries)
        pref =  [0] * (n + 1) 

        for i in range(1, n + 1): 
            pref[i] = pref[i - 1] + candiesCount[i - 1]

        for i in range(len(queries)): 
            t = queries[i][0]
            time = queries[i][1]
            limit = queries[i][2]

            maxi = pref[t + 1] - 1
            mini = pref[t] // limit

            if (time <= maxi and time >= mini): 
                res[i] = True 
            else: 
                res[i] = False 

        return res
            