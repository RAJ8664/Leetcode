import math

class Solution:
    def get_dist(self, first, second) -> float:
        x1, y1 = first[0], first[1]
        x2, y2 = second[0], second[1]

        return math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
        
    
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        n = len(points) 
        count = 0
        mp = dict()
        for i in range(n):
            for j in range(n):
                if i == j: continue
                dist = self.get_dist(points[i], points[j])
                if dist in mp:
                    mp[dist] += 1
                else:
                    mp[dist] = 1 
            for (key, val) in mp.items():
                count += val * (val - 1)
            mp.clear()
        return count
            
                
                    
        