class Solution {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (i * i + j * j == k * k)
                        count++;
                }
            }
        }
        return count;
    }
}