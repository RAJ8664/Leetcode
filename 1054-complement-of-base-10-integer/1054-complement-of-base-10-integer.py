class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0: return 1
        return n ^ ((1 << n.bit_length()) - 1)
        