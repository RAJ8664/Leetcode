class Solution:
    def digitFrequencyScore(self, n: int) -> int:
        freq = [0] * 10
        dig = set()
         
        def get_freq(n: int) -> None:
            temp = n
            while temp > 0:
                freq[temp % 10] += 1
                dig.add(temp % 10)
                temp = temp // 10

        get_freq(n)
        
        res = 0
        for ele in dig:
            res += ele * freq[ele]

        return res 
            
        
        
        
             