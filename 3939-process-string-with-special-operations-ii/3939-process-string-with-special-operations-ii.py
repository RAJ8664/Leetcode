class Solution:
    def processStr(self, s: str, k: int) -> str:
        length = 0
        for c in s:
            if c == "*":
                if length:
                    length -= 1
            elif c == "#":
                length *= 2
            elif c == "%":
                pass
            else:
                length += 1
        if k + 1 > length:
            return "."
        for c in reversed(s):
            if c == "*":
                length += 1
            elif c == "#":
                if k + 1 > (length + 1) // 2:
                    k -= length // 2
                length = (length + 1) // 2
            elif c == "%":
                k = length - k - 1
            else:
                if k + 1 == length:
                    return c
                length -= 1
        return "."