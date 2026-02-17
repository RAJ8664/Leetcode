class Solution:
    
    def count_set_bits(self, num):
        temp = num
        count = 0
        while temp > 0: 
            count += 1
            temp = (temp & (temp - 1))
        return count

    def format_time(self, h, m): 
        formatted_time = str(h) + ":"
        if m < 10:
            formatted_time += "0"
        formatted_time += str(m)
        return formatted_time

    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        res = []
        for h in range(12):
            for m in range(60):
                if self.count_set_bits(h) + self.count_set_bits(m) == turnedOn:
                    res.append(self.format_time(h, m))
        return res
        
        