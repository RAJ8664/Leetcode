class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        n = len(nums)
        res = []
        
        for ele in nums:
            if ele < pivot:
                res.append(ele)

        for ele in nums:
            if ele == pivot:
                res.append(ele)

        for ele in nums:
            if ele > pivot:
                res.append(ele)

        return res 