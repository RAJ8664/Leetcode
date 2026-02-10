class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)
        maxi = 0
        for i in range(0, n):
            odd_set = set()
            even_set = set()
            for j in range(i, n): 
                if nums[j] % 2 == 0: 
                    even_set.add(nums[j])
                else: 
                    odd_set.add(nums[j])
                if len(even_set) == len(odd_set): 
                    maxi = max(maxi, j - i + 1)   
        return maxi 