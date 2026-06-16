class Solution:
    def processStr(self, s: str) -> str:
        res = ""
        
        for ch in s:
            if ch.isalpha():
                res += ch
            elif ch == '*':
                if len(res) >= 1:
                    res = res[:-1]
            elif ch == '#':
                res += res
            elif ch == '%':
                res = res[::-1]
        
        return res