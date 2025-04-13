class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        int res = 1;
        while (res <= n) res *= 2;
        res >>= 1;
        return 2 * res;
    }
}