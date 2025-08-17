class Solution {
    private int mod = (int)(1e9 + 7);
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int query[] : queries) {
            int l = query[0], r = query[1], k = query[2], v = query[3];
            for (int i = l; i <= r; i += k) {
                long current = nums[i]; current = current * v;
                if (current >= mod) 
                    current %= mod;
                nums[i] = (int)(current);
            }
        }    
        int xor = 0;
        for (int ele : nums) 
            xor ^= ele;
        return xor;
    }
}