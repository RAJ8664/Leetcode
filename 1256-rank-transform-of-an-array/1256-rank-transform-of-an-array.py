class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        n = len(arr)

        original = [0] * n 
        for i in range(n):
            original[i] = arr[i]
            
        mp = dict()
        arr.sort()

        curr_idx = 1
        for ele in arr:
            if ele not in mp:
                mp[ele] = curr_idx
                curr_idx += 1

        res = []
        for i in range(n):
            res.append(mp[original[i]]) 
        
        return res
        