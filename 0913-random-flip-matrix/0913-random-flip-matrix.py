class Solution:

    def __init__(self, m: int, n: int):
        self.m = m
        self.n = n
        self.st = set()
        self.total = m * n
        
    def flip(self) -> List[int]:
        curr = random.randint(0, self.total - 1)
        while curr in self.st:
            curr = random.randint(0, self.total - 1)
        self.st.add(curr)
        return [curr // self.n, curr % self.n]

    def reset(self) -> None:
        self.st.clear()
        


# Your Solution object will be instantiated and called as such:
# obj = Solution(m, n)
# param_1 = obj.flip()
# obj.reset()