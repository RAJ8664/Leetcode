class Solution:
    MOD = 10**9 + 7

    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        pref_hash = [0] * (n + 1)
        pref_sum = [0] * (n + 1)
        pow10 = [0] * (n + 1)
        count = [0] * (n + 1)

        pow10[0] = 1
        for i in range(1, n + 1):
            pow10[i] = (pow10[i - 1] * 10) % self.MOD

        for i in range(1, n + 1):
            d = ord(s[i - 1]) - ord('0')
            pref_hash[i] = pref_hash[i - 1]
            pref_sum[i] = pref_sum[i - 1]
            count[i] = count[i - 1]
            if d != 0:
                pref_hash[i] = (pref_hash[i] * 10 + d) % self.MOD
                pref_sum[i] += d
                count[i] += 1

        ans = [0] * len(queries)
        for i, (l, r) in enumerate(queries):
            l += 1
            r += 1

            k = count[r] - count[l - 1]
            total_sum = pref_sum[r] - pref_sum[l - 1]
            if k == 0 or total_sum == 0:
                ans[i] = 0
                continue

            x = (pref_hash[r] - (pref_hash[l - 1] * pow10[k]) % self.MOD) % self.MOD
            ans[i] = (x * (total_sum % self.MOD)) % self.MOD

        return ans
        