class Solution:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        n, m = len(s), len(t)

        if n > m:
            return False

        left = [-1] * n
        right = [-1] * n

        idx = 0
        for i in range(n):
            while idx < m and s[i] != t[idx]:
                idx += 1
            if idx >= m:
                break
            left[i] = idx
            idx += 1

        if left[n - 1] != -1:
            return True

        idx = m - 1
        for i in range(n - 1, -1, -1):
            while idx >= 0 and s[i] != t[idx]:
                idx -= 1
            if idx < 0:
                break
            right[i] = idx
            idx -= 1

        for i in range(n):
            if ((i == 0 or left[i - 1] != -1) and (i == n - 1 or right[i + 1] != -1)):
                l = -1
                r = m
                if i - 1 >= 0:
                    l = left[i - 1]
                if i + 1 < n:
                    r = right[i + 1]
                if l + 1 < r:
                    return True

        return False
        