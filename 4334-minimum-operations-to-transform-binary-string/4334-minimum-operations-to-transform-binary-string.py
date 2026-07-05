class Solution:
    def minOperations(self, s1: str, s2: str) -> int:
        n = len(s1)
        m = len(s2)

        s = list(s1)
        count = 0
        diff = 0
        for i in range(n):
            if s1[i] != s2[i]:
                diff += 1

        if diff == 0:
            return 0

        if s1 == "1" and s2 == "0":
            return -1

        for i in range(n):
            if s[i] == s2[i]:
                continue

            count += 1
            if s[i] == '1':
                if i == n - 1:
                    count += 1
                else:
                    count += s[i + 1] == '0'
                    s[i + 1] = '0'

        return count
        