class Solution:
    def consecutiveSetBits(self, n: int) -> bool:
        binary = ""
        while n > 0:
            if n % 2 == 0:
                binary += "0"
            else:
                binary += "1"
            n = n // 2
        binary = binary[::-1]
        count = 0
        for i in range(0, len(binary) - 1):
            if binary[i] == '1' and binary[i + 1] == '1':
                count += 1
        return count == 1
        

        