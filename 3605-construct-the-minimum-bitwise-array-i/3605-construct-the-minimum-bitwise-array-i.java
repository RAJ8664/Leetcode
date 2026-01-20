class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
         int n = nums.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = solve(nums.get(i));
        return res;
    }
    
    private static int solve(int current) {
        for (int ans = 0; ans <= current; ans++) {
            if ((ans | (ans + 1)) == current) {
                return ans;
            }
        }
        return -1; 
    }
}