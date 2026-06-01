class Solution:
    def minimumCost(self, cost: List[int]) -> int:
        n = len(cost)
        cost.sort(reverse=True)
        res = 0
        for i, ele in enumerate(cost):
            if i % 3 != 2:
                res += ele 
        return res