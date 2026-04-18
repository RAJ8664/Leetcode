class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        maxi = 1
        res = ""
        for i in range(n):
            left, right = i, i
            while left - 1 >= 0 and right + 1 < n and s[left - 1] == s[right + 1]:
                left -= 1
                right += 1

            if right - left + 1 > maxi:
                maxi = right - left + 1
                res = s[left:right + 1] 
            
            left, right = i, i + 1
            if right < n and s[left] == s[right]:
                if right - left + 1 > maxi:
                    maxi = right - left + 1
                    res = s[left:right + 1]

            temp = 0
            while right < n and s[left] == s[right] and left - 1 >= 0 and right + 1 < n and s[left - 1] == s[right + 1]:
                left -= 1
                right += 1
                temp = 1
            
            if temp == 1 and right - left + 1 > maxi:
                maxi = right - left + 1
                res = s[left: right + 1]
        if maxi == 1:
            return str(s[0])
        return res 