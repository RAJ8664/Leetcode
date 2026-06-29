class Solution:
    def optimalDivision(self, nums: List[int]) -> str:
        n = len(nums)
        # a, b, c
        # Don't you think its alsways better to do a / (x1 / x2 / x3 / x5/ ...) ? Let's try

        if n == 1:
            return str(nums[0])
        if n == 2:
            return str(nums[0]) + "/" + str(nums[1])
        
        generate = "("
        for i in range(1, n):
            if i == n - 1:
                generate += str(nums[i]) + ")"
                pass
            else:
                generate += str(nums[i]) + "/"

        return str(nums[0]) + "/" + generate
        
        
        