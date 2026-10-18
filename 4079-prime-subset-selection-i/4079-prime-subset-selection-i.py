class Solution:
    def maxPrimes(self, n: int, s: int) -> list[int]:
        if s <= 1:
            return []

        is_prime = [1] * (n + 1)

        is_prime[1] = 0
        for i in range(2, n + 1):
            if is_prime[i] == 1:
                for j in range(i * i, n + 1, i):
                    is_prime[j] = 0

        curr_sum = 0
        res = []
        for i in range(2, len(is_prime)):
            if is_prime[i] == 1:
                if curr_sum + i <= s:
                    curr_sum += i
                    res.append(i)

        return res