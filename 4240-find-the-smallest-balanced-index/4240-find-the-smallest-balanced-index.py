class Solution:
    def smallestBalancedIndex(self, nums: list[int]) -> int:
        n = len(nums)

        for i in range(n): 
            nums[i] = nums[i] % (int)(10**9 + 7)

        if (n == 1): 
            return -1
            
        pref_sum = [0] * n
        pref_sum[0] = nums[0]
        for i in range(1, n):
            pref_sum[i] = pref_sum[i - 1] + nums[i] 

        suff = 1
        ans = -1 
        for i in range(n - 1, -1, -1):
            if i == 0:
                if suff == 0:
                    ans = i
            elif i == n - 1:
                if pref_sum[i - 1] == 1:
                    ans = i
            else:
                if pref_sum[i - 1] == suff:
                    ans = i

            if (suff > pref_sum[n - 1]): 
                break
            suff *= nums[i]
        
        return ans
                  
             