class Solution:
    def toggleLightBulbs(self, bulbs: list[int]) -> list[int]:
        count = dict()
        res = []
        for ele in bulbs: 
            count[ele] = count.get(ele, 0) + 1
        for k, v in count.items(): 
            if v % 2 == 1: 
                res.append(k)
        res.sort()
        return res
        