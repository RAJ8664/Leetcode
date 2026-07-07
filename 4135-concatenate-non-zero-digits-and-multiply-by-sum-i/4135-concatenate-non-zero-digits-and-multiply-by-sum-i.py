class Solution:
    def sumAndMultiply(self, n: int) -> int:
        def form(num: int) -> tuple:
            res = 0
            sum = 0
            num = list(str(num))
            for i in range(len(num)):
                if int(num[i]) != 0:
                    res = res * 10 + int(num[i])
                sum += int(num[i])
            
            return (res, sum)

        x, sum = form(n)
        return x * sum

        