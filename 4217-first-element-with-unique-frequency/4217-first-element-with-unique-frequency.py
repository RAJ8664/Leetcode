class Solution:
    def firstUniqueFreq(self, nums: List[int]) -> int:
        n = len(nums)
        count = dict()
        freq_count = dict()
        for ele in nums:
            count[ele] = count.get(ele, 0) + 1
        for k, v in count.items():
            freq_count[v] = freq_count.get(v, 0) + 1
        st = set()
        for k, v in freq_count.items():
            if v == 1:
                st.add(k)
        for ele in nums:
            if (count.get(ele, 0)) in st:
                return ele
        return -1

