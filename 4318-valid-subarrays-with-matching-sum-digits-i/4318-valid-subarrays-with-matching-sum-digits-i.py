class Solution:
    def is_valid(self, sum: int, x: int) -> int:
        temp = sum
        
        while temp >= 10:
            temp = temp // 10
        
        if temp == x: return 1
        return 0

    def countValidSubarrays(self, nums: list[int], x: int) -> int:
        count = 0
        
        for i in range(len(nums)):
            curr_sum = 0
            for j in range(i, len(nums)):
                curr_sum += nums[j]
                
                if curr_sum % 10 == x and self.is_valid(curr_sum, x) == 1:
                    count += 1

        return count