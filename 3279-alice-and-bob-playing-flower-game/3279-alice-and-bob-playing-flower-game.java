class Solution {
    public long flowerGame(int n, int m) {
        long res = 0;
        int countEven = m / 2, countOdd = (m + 1) / 2;
        res += (n / 2) * 1L * countOdd + ((n + 1) / 2) * 1L * (countEven);
        return res;
    }
}