class Solution:
    def __init__(self):
        self.limit = (10 ** 5 + 100)
        self.is_prime = [1] * self.limit

    def sieve(self, nums: list[int]) -> None:
        self.is_prime[0] = self.is_prime[1] = 0; 
        for i in range(2, (int)(self.limit ** 0.5) + 1):
            if self.is_prime[i]:
                for j in range(i * i, self.limit, i):
                    self.is_prime[j] = 0

    def get_next_non_prime(self, n: int) -> int:
        while self.is_prime[n]:
            n += 1
        return n

    def get_next_prime(self, n: int) -> int:
        while not self.is_prime[n]:
            n += 1
        return n
     
    def minOperations(self, nums: list[int]) -> int:
        n = len(nums)

        self.sieve(nums) 

        res = 0
        for i in range(0, n):
            if i % 2 == 0:
                if self.is_prime[nums[i]]: continue
                else:
                    res += self.get_next_prime(nums[i]) - nums[i]
            else:
                if not self.is_prime[nums[i]]: continue
                else:
                    res += self.get_next_non_prime(nums[i]) - nums[i]
        
        return res