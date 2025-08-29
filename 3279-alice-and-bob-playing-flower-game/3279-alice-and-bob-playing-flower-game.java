class Solution {
    public long flowerGame(int n, int m) {
        long res = 0;
        int countEven = 0, countOdd = 0;
        for (int i = 1; i <= m; i++) if (i % 2 == 0) countEven++; else countOdd++;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                res += countOdd;
            }
            else 
                res += countEven;
        } 
        return res;
    }
}