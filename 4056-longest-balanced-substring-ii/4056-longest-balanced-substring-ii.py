class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        maxi = 0

        curr = 1
        for i in range(1, n):
            if s[i] == s[i - 1]:
                curr += 1
            else:
                maxi = max(maxi, curr)
                curr = 1
        maxi = max(maxi, curr)

        def two_char(x, y):
            map = {0: -1}
            diff = 0
            best = 0

            for i, ch in enumerate(s):
                if ch == x:
                    diff += 1
                elif ch == y:
                    diff -= 1
                else:
                    map = {0: i}
                    diff = 0
                    continue
                if diff in map:
                    best = max(best, i - map[diff])
                else:
                    map[diff] = i

            return best

        maxi = max(maxi, two_char("a", "b"))
        maxi = max(maxi, two_char("b", "c"))
        maxi = max(maxi, two_char("a", "c"))

        count_a = 0
        count_b = 0
        count_c = 0
        vis = {(0, 0): -1}
        for i, ch in enumerate(s):
            if ch == "a":
                count_a += 1
            elif ch == "b":
                count_b += 1
            elif ch == "c":
                count_c += 1
            key = (count_b - count_a, count_c - count_a)
            if key in vis:
                maxi = max(maxi, i - vis[key])
            else:
                vis[key] = i

        return maxi