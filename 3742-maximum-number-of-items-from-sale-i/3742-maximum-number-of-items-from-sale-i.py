class Solution:
    def get_extra(self, items, extra) -> None:
        n = len(items)
        for i in range(n):
            for j in range(n):
                if i == j: continue
                if items[j][0] % items[i][0] == 0:
                    extra[i] += 1
    
    def solve(self, idx, items, budget, dp, used, extra) -> int:
        if idx >= len(items) or budget <= 0:
            return 0

        if dp[idx][budget][used] != -1:
            return dp[idx][budget][used]

        op1, op2 = 0, 0

        # I don't want to buy this product
        op1 = self.solve(idx + 1, items, budget, dp, 0, extra)

        # I want to buy this product
        if items[idx][1] <= budget:
            op2 += 1
            if used == 1:
                op2 += self.solve(idx, items, budget - items[idx][1], dp, used, extra) 
            else:
                # To eleminate this, precompute how many extra items we can get for free if we buy this product
                # for i in range(len(items)):
                    # if i == idx: continue
                    # if items[i][0] % items[idx][0] == 0:
                        # op2 += 1
                op2 += extra[idx]
                op2 += self.solve(idx, items, budget - items[idx][1], dp, 1, extra)
                
        dp[idx][budget][used] = max(op1, op2)
        return max(op1, op2) 

    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        dp = [[[-1] * 2 for _ in range(budget + 1)] for _ in range(n)] 
        extra = [0] * n 

        self.get_extra(items, extra)   

        return self.solve(0, items, budget, dp, 0, extra) 