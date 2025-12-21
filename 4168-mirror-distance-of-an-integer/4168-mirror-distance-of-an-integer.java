class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - Rev(n));
    }
    private int Rev(int n) {
        int res = 0, temp = n;
        while (temp > 0) {
            res = res * 10 + temp % 10;
            temp /= 10;
        }
        return res;
    }
}