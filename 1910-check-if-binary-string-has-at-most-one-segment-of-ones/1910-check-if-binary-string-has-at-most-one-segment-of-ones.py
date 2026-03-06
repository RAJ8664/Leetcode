class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        n = len(s)
        count = 0
        cont = 0
        for i in range(n): 
            if s[i] == '1':
                cont += 1
            else:  
                if (cont > 0): 
                    count += 1
                cont = 0
        if cont > 0: 
            count += 1
        return count <= 1