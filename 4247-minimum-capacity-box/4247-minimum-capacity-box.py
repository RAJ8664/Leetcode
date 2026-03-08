class Solution:
    def minimumIndex(self, capacity: list[int], itemSize: int) -> int:
        n = len(capacity)
        res = -1
        currMini = (int)(10**9) 
        for i in range(n):
            if capacity[i] >= itemSize: 
                if capacity[i] < currMini: 
                    currMini = capacity[i]
                    res = i  
            
        return res 