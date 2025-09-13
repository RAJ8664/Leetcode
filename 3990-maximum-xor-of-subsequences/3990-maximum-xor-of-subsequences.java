class Solution {
    public int maxXorSubsequences(int[] nums) {
        int n = nums.length;
        int freq[] = new int[32];
        for (int ele : nums) {
            for (int i = 31; i >= 0; i--) {
                if ((ele & (1 << i)) == 0) 
                    continue;
                if (freq[i] == 0) {
                    freq[i] = ele;
                    break;
                }
                ele ^= freq[i];
            }
        }
        int ans = 0;
        for (int i = 31; i >= 0; i--) 
            ans = Math.max(ans, ans ^ freq[i]);
        return ans; 
    }
}