class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);
        int curr = k; 
        while (true) {
            if (!set.contains(curr))
                return curr;
            curr += k;
        }
    }
}