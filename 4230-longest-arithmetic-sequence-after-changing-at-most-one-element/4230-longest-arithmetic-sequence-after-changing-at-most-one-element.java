class Solution {
    private int pref[], suff[]; 
    
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        if (n <= 2) 
            return n;
        
        pref = new int[n];
        suff = new int[n];
        for (int i = 0; i < n; i++) {
            pref[i] = 2;
            suff[i] = 2;
        } 
        
        for (int i = 2; i < n; i++)
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) 
                pref[i] = pref[i - 1] + 1;
        for (int i = n - 3; i >= 0; i--) 
            if (nums[i + 2] - nums[i + 1] == nums[i + 1] - nums[i]) 
                suff[i] = suff[i + 1] + 1;
        
        int ans = 0;
        for (int ele : pref) 
            ans = Math.max(ans, ele);
        for (int ele : suff)
            ans = Math.max(ans, ele);

        ans += 1;
        for (int i = 1; i < n - 1; i++) {
            if (Math.abs(nums[i + 1] - nums[i - 1]) % 2 == 1) 
                continue;
            int diff = (nums[i + 1] - nums[i - 1]) / 2;
            
            int left = 1, right = 1;
            
            if (i >= 2 && nums[i - 1] - nums[i - 2] == diff) {
                left = pref[i - 1];
            }
            
            if (i + 2 < n && nums[i + 2] - nums[i + 1] == diff) {
                right = suff[i + 1];
            }
            
            ans = Math.max(ans, left + right + 1); 
        }
        
        return Math.min(ans, n); 
    }
}