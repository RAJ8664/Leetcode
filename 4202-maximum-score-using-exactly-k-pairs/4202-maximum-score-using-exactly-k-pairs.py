class Solution:

    def solve(self, i, j, k, arr, brr, dp):
        if k == 0: 
            return 0
        if (i == len(arr) or j == len(brr) and k > 0): 
            return -10**18 / 10
        
        if dp[i][j][k] != -10**18: 
            return dp[i][j][k]
        
        op1 = arr[i] * brr[j] + self.solve(i + 1, j + 1, k - 1, arr, brr, dp)
        op2 = self.solve(i + 1, j, k, arr, brr, dp)
        op3 = self.solve(i, j + 1, k, arr, brr, dp)

        dp[i][j][k] = max(op1, op2, op3)
        return dp[i][j][k]

    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        n = len(nums1)
        m = len(nums2)

        dp = [[[-10**18] * (k + 1) for _ in range(m + 1)] for _ in range(n + 1)]
        
        return self.solve(0, 0, k, nums1, nums2, dp)
