class Solution:
    def countGoodSubarrays(self, nums):
        n = len(nums)
        
        L = [-1] * n
        R = [n] * n
        
        stack = []
        for i in range(n):
            while stack and nums[stack[-1]] < nums[i]:
                stack.pop()
            L[i] = stack[-1] if stack else -1
            stack.append(i)
        
        stack = []
        for i in range(n - 1, -1, -1):
            while stack and nums[stack[-1]] <= nums[i]:
                stack.pop()
            R[i] = stack[-1] if stack else n
            stack.append(i)
        
        MAXB = 31
        prev_pos = [[-1] * n for _ in range(MAXB)]
        next_pos = [[n] * n for _ in range(MAXB)]
        
        last = [-1] * MAXB
        for i in range(n):
            for b in range(MAXB):
                if (nums[i] >> b) & 1:
                    last[b] = i
                prev_pos[b][i] = last[b]
        
        last = [n] * MAXB
        for i in range(n - 1, -1, -1):
            for b in range(MAXB):
                if (nums[i] >> b) & 1:
                    last[b] = i
                next_pos[b][i] = last[b]
        
        ans = 0
        for i in range(n):
            left = L[i] + 1
            right = R[i] - 1
            for b in range(MAXB):
                if ((nums[i] >> b) & 1) == 0:
                    l_bad = prev_pos[b][i]
                    r_bad = next_pos[b][i]
                    if l_bad >= left:
                        left = l_bad + 1
                    if r_bad <= right:
                        right = r_bad - 1
            
            if left <= i <= right:
                ans += (i - left + 1) * (right - i + 1)
        
        return ans
        