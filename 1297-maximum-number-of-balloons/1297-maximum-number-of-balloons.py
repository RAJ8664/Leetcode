class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        mp = dict()
        for ch in text:
            mp[ch] = mp.get(ch, 0) + 1
        target = "balon"
        res = 10 ** 9
        for ch in target:
            if ch == 'o':
                res = min(res, mp.get(ch, 0) // 2)
            elif ch == 'l':
                res = min(res, mp.get(ch, 0) // 2)
            else: res = min(res, mp.get(ch, 0))
        return res
        