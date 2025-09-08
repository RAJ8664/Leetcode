class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i <= n; i++) {
            if (n < i) continue; 
            if (valid(i) && valid(n - i)) {
                return new int[]{i, n - i};
            }
        } 
        return new int[]{-1, -1};
    }
    private boolean valid(int n) {
        int temp = n;
        while (temp > 0) {
            if (temp % 10 == 0)
                return false;
            temp /= 10;
        }
        return true;
    }
}