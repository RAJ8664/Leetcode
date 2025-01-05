class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for(int i = 0; i < n; i++) {
            int mul = nums[i], gcd = nums[i], lcm = nums[i];
            for(int j = i + 1; j < n; j++) {
                mul *= nums[j];
                gcd = gcd(gcd, nums[j]);
                lcm = lcm(lcm, nums[j]);
                if(mul == gcd * lcm) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}