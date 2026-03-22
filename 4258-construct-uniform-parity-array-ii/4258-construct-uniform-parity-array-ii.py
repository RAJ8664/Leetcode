class Solution:

    def check(self, arr, target): 
        n = len(arr)
        low = 0
        high = n - 1
        ans = -1
        while (low <= high): 
            mid = low + (high - low) // 2
            if (arr[mid] <= target): return True
            else: high = mid - 1
        return False

    def solve(self, arr): 
        n = len(arr)

        odd = []
        even = []

        for ele in arr: 
            if ele % 2 == 0: even.append(ele)
            else: odd.append(ele)

        even.sort()
        odd.sort()

        flag = True
        
        # Lets try to make all even first
        for i in range(n): 
            if arr[i] % 2 == 0: continue
            else: 
                if (len(odd) <= 1): 
                    flag = False
                    break
                else:
                    need = arr[i] - 1 
                    # Check if there exist any number in odd that is less than need (ele = 5, need = 4, if 3 is there,, done)
                    if (self.check(odd, need)): continue
                    else: 
                        flag = False
                        break

        if flag: return True

        flag = True 
        # Lets try to make all odd
        for i in range(n): 
            if arr[i] % 2 == 1: continue
            else: 
                if (len(odd) == 0): 
                    flag = False
                    break
                else: 
                    need = arr[i] - 1
                    if (self.check(odd, need)): 
                        continue
                    else: 
                        flag = False
                        break
        
        return flag
                
    def uniformArray(self, nums1: list[int]) -> bool:
        n = len(nums1)
        return self.solve(nums1)
        