class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> res = new ArrayList<>();
        int current = 0;
        for (int ele : nums) {
            current = ((current << 1) + ele) % 5;
            if (current == 0) 
                res.add(true);
            else 
                res.add(false);
        }
        return res;
    }
}