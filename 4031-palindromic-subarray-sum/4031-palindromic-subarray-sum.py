class Solution:
    def getSum(self, nums: List[int]) -> int:
        # Manacher ??
        n = len(nums)

        pref = [0] * n
        pref[0] = nums[0]
        for i in range(1, n):
            pref[i] = pref[i - 1] + nums[i]

        t = [None]
        for x in nums:
            t.append(x)
            t.append(None)

        m = len(t)
        p = [0] * m

        center = right = 0
        for i in range(m):
            mirror = 2 * center - i
            if i < right:
                p[i] = min(right - i, p[mirror])
            while (i - p[i] - 1 >= 0 and i + p[i] + 1 < m and t[i - p[i] - 1] == t[i + p[i] + 1]):
                p[i] += 1
            if i + p[i] > right:
                center = i
                right = i + p[i]

        ans = 0
        for c in range(m):
            r = p[c]
            if r == 0:
                continue
            L = (c - r) // 2
            R = (c + r) // 2 - 1
            cur = pref[R]
            if L:
                cur -= pref[L - 1]
            ans = max(ans, cur)
        ans = max(ans, max(nums))

        return ans