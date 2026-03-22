class Solution:
    def minRemovals(self, nums, target):
        n = len(nums)
        
        half = n // 2
        left = nums[:half]
        right = nums[half:]
        
        from collections import defaultdict
        
        # left half
        left_map = defaultdict(int)  # xor -> max size
        
        for mask in range(1 << len(left)):
            x = 0
            cnt = 0
            for i in range(len(left)):
                if mask & (1 << i):
                    x ^= left[i]
                    cnt += 1
            left_map[x] = max(left_map[x], cnt)
        
        # right half
        max_keep = -1
        
        for mask in range(1 << len(right)):
            x = 0
            cnt = 0
            for i in range(len(right)):
                if mask & (1 << i):
                    x ^= right[i]
                    cnt += 1
            
            need = target ^ x
            
            if need in left_map:
                max_keep = max(max_keep, cnt + left_map[need])
        
        if max_keep == -1:
            return -1
        
        return n - max_keep 