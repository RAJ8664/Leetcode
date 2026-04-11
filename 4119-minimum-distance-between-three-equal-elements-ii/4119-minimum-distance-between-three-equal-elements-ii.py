class Solution:
    def minimumDistance(self, nums: List[int]) -> int:
        n = len(nums)
        map = dict()

        for i in range(n):
            if nums[i] in map:
                map[nums[i]].append(i)
            else:
                map[nums[i]] = []
                map[nums[i]].append(i)

        mini = float("inf")
        for key, val in map.items():
            if len(val) < 3:
                continue
            for i in range(len(val) - 2):
                mini = min(
                    mini,
                    abs(val[i] - val[i + 1])
                    + abs(val[i + 1] - val[i + 2])
                    + abs(val[i + 2] - val[i]),
                )

        if mini == float("inf"):
            return -1
        return (int)(mini)

