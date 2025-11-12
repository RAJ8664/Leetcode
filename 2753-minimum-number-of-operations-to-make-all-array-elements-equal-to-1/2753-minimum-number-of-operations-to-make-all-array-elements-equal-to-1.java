class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count = 0, gcd = 0;
        for (int x : nums) {
            if (x == 1) 
               count++; 
            gcd = gcd(gcd, x);
        }
        if (count > 0) 
            return n - count;
        if (gcd > 1) 
            return -1;
        int minLen = n;
        for (int i = 0; i < n; i++) {
            int currentGcd = 0;
            for (int j = i; j < n; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if (currentGcd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen + n - 2;
    }
    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);        
    }
}