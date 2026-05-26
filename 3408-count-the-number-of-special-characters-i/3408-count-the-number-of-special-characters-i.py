class Solution:
    def numberOfSpecialChars(self, w: str) -> int:
        return (s:={*w}) and sum(chr(ord(c) + 32) in s for c in s)