class Solution:
    def solve(self, s: str) -> int:
        count1, count0, diff, res = 0, 0, 0, 0
        n = len(s)
        for ch in s:
            if ch == '1':
                count1 += 1
            else:
                count0 += 1
        cnt1, cnt0 = 0, 0
        map = {}
        map[0] = -1
        for i, ch in enumerate(s):
            if ch == '1':
                cnt1 += 1
                diff += 1
            else:
                cnt0 += 1
                diff -=1
            if diff in map:
                res = max(res, i - map[diff])
            if (diff + 2) in map and (count1 - cnt1) > 0:
                res = max(res, i - map[diff + 2])
            if (diff - 2) in map and (count0 - cnt0) > 0:
                res = max(res, i - map[diff - 2])
            if diff not in map:
                map[diff] = i
        return res

    def longestBalanced(self, s: str) -> int:
        return max(self.solve(s), self.solve(s[::-1]))