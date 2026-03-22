class Solution:

    def solve(self, arr): 
        n = len(arr)
        odd_count = 0
        even_count = 0
        
        for ele in arr: 
            if ele % 2 == 0: even_count += 1
            else: odd_count += 1

        flag = True 
        for i in range(n): 
            if arr[i] % 2 == 0: 
                continue
            else: 
                if odd_count > 1: 
                    continue
                else: 
                    flag = False

        if flag: return True

        flag = True
        for i in range(n): 
            if arr[i] % 2 == 1: 
                continue
            else: 
                if odd_count > 0: 
                    continue
                else: 
                    flag = False 

        return flag

    def uniformArray(self, nums1: list[int]) -> bool:
        n = len(nums1)
        return self.solve(nums1) 