class Solution {
    public int minSensors(int n, int m, int k) {
        int temp = 2 * k + 1;
        return ((n + temp - 1) / temp) * ((m + temp - 1) / temp);
    }
}