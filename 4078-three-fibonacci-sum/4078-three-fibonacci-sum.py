class Solution:
    def threeFibonacciSum(self, n: int) -> bool:
        temp = []
        a, b = 0, 1
        temp.append(0)
        temp.append(1)
        while True:
            if a + b > n:
                break
            temp.append(a + b)
            next = a + b
            a = b
            b = next

        for i in range(len(temp) - 2):
            if temp[i] + temp[i + 1] + temp[i + 2] > n:
                return False
            else:
                if temp[i] + temp[i + 1] + temp[i + 2] == n:
                    return True

        return False 
            
        
        