class Solution:
    def divisibleGame(self, nums: list[int]) -> int:
        n = len(nums)
        
        MOD = 10 ** 9 + 7
        maxi = -10 ** 9
        res = -10 ** 9

        st = set()
        for ele in nums:
            for j in range(1, (int)(math.sqrt(ele)) + 1):
                if ele % j == 0:
                    st.add(j)
                    if ele / j != j:
                        st.add((int)(ele // j))

        st.remove(1)
        if len(st) == 0:
            sum = 0
            for ele in nums:
                sum = -ele
            sum = (sum * 2) % MOD
            return sum
        
        divisors = list(st)
        divisors.sort()

        for k in divisors:
            arr = []
            for ele in nums:
                if ele % k == 0:
                    arr.append(ele)
                else:
                    arr.append(-ele)

            sum = 0
            curr_maxi = -10 ** 9
            for ele in arr:
                sum += ele
                curr_maxi = max(curr_maxi, sum)
                if sum < 0:
                    sum = 0

            if curr_maxi > maxi:
                maxi = curr_maxi
                res = (curr_maxi % MOD * k % MOD) % MOD

        return res % MOD
                
             

        