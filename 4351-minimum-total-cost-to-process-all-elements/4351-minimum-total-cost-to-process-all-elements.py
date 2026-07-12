class Solution:
    def minimumCost(self, nums: list[int], k: int) -> int:
        n = len(nums)

        MOD = 10 ** 9 + 7 
        curr_cost = 1
        curr_resource = k
        total_cost = 0
        
        for ele in nums:
            if ele > curr_resource:
                req = ele - curr_resource 

                # How many times we need to increment curr_cost so that we can reach this req number of resources
                times = math.ceil(req / k)

                curr_resource += times * k
                curr_resource -= ele

                next = (curr_cost + times - 1)
                prev = (curr_cost - 1)

                next_cost = (next * (next + 1) // 2)
                prev_cost = (prev * (prev + 1) // 2)

                curr_cost += times 

                total_cost = (total_cost + (next_cost - prev_cost)) % MOD
            else:
                curr_resource -= ele

        return total_cost
        