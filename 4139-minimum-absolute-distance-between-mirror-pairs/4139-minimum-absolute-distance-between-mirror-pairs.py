class Solution:
    def get_reverse(self, n: int) -> int:
        res = 0
        while n:
            res = res * 10 + n % 10
            n = n // 10
        return res
        
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        n = len(nums)
        mp = dict()

        mini = 10 ** 9
        for i in range(n - 1, -1, -1):
            rev = self.get_reverse(nums[i])
            if rev in mp:
                mini = min(mini, abs(i - mp.get(rev))) 
            mp[nums[i]] = i

        if mini == 10 ** 9: return -1
        return mini
                
            
    
        