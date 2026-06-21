class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        count = 0
        costs.sort()
        for ele in costs:
            if ele <= coins:
                coins = coins - ele
                count += 1
        return count 
        