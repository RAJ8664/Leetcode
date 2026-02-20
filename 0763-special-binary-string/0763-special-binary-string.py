class Solution:
    def makeLargestSpecial(self, s: str) -> str:
        n = len(s)
        if n <= 2:
            return s
        list = []
        count = 0
        start = 0
        for i in range(n):
            if s[i] == '1':
                count += 1
            else:
                count -= 1
            if count == 0: 
                next = self.makeLargestSpecial(s[start + 1:i])
                list.append("1" + next + "0")
                start = i + 1 
        list.sort()
        list.reverse()
        res = ""
        for string in list:
            res += string
        return res 
