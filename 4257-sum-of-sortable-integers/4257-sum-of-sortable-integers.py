class Solution:
    def sortableIntegers(self, nums):
        n = len(nums)
        factors = self.getFactors(n)
        res = 0

        for k in factors:
            if self.check(k, nums):
                res += k

        return res

    def check(self, k, arr):
        n = len(arr)
        prev_max = float('-inf')

        for start in range(0, n, k):
            curr_min = float('inf')
            curr_max = float('-inf')
            drop = 0

            for j in range(k):
                val = arr[start + j]
                curr_min = min(curr_min, val)
                curr_max = max(curr_max, val)

                next_val = arr[start + (j + 1) % k]
                if val > next_val:
                    drop += 1

            if drop > 1:
                return False

            if start > 0 and curr_min < prev_max:
                return False

            prev_max = curr_max

        return True

    def getFactors(self, num):
        res = []
        i = 1
        while i * i <= num:
            if num % i == 0:
                res.append(i)
                if num // i != i:
                    res.append(num // i)
            i += 1
        return res