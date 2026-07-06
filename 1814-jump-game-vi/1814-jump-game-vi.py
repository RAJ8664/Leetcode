class Solution:
    def maxResult(self, nums: List[int], k: int) -> int:
        n = len(nums)

        dp = [-10**9] * n
        dp[0] = nums[0]
        dq = deque([0])

        # for i in range(1, n):
        #     for j in range(i, i - k - 1, -1):
        #         if j >= 0:
        #             dp[i] = max(dp[i], nums[i] + dp[j])

        for i in range(1, n):
            while dq and dq[0] < i - k:
                dq.popleft()

            dp[i] = nums[i] + dp[dq[0]]

            while dq and dp[dq[-1]] <= dp[i]:
                dq.pop()

            dq.append(i)

        return dp[n - 1]
                
        