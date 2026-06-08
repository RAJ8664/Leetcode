class Solution:
    def generateValidStrings(self, n: int, k: int) -> list[str]:
        def generate(idx, prev, s, sum):
            if idx == n:
                if sum <= k:
                    res.append(s)
                return
            generate(idx + 1, False, s + '0', sum)
            if not prev:
                generate(idx + 1, True, s + '1', sum + idx)
        res = []
        generate(0, False, "", 0)
        return res    

