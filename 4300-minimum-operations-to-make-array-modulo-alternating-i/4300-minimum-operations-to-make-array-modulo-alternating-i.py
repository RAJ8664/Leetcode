class Solution:
    def minOperations(self, nums: list[int], k: int) -> int:
        n = len(nums)
        count = 10 ** 9
        for x in range(k):
            for y in range(k):
                if x == y: continue
                current = 0
                for i in range(n):
                    if i % 2 == 0:
                        current += min((nums[i] % k - x + k) % k, (x - nums[i] % k + k) % k) 
                    else:
                        current += min((nums[i] % k - y + k) % k, (y - nums[i] % k + k) % k) 
                count = min(count, current)
        
        return count
                 
        