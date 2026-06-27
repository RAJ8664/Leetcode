class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        n = len(nums)
        freq = collections.defaultdict(int)
        for ele in nums:
            freq[ele] += 1
        maxi = 0
        for x in freq:
            if x == 1:
                if freq[1] % 2 == 1:
                    count = freq[1]
                else:
                    count = freq[1] - 1
            else:
                curr = x
                count = 0
                flag = False
                while curr in freq and freq[curr] >= 2 and not flag:
                    if curr * curr in freq:
                        count += 2
                        curr = curr * curr
                    else:
                        count += 1
                        flag = True
                if not flag and curr in freq:
                    count += 1
            maxi = max(maxi, count)

        return maxi

        