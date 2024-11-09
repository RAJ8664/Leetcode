class Solution {
    public long minEnd(int n, int x) {
        long current = x;
        while ((n - 1) > 0) {
            n--;
            current = (current + 1) | x;
        }
        return current;
    }
}