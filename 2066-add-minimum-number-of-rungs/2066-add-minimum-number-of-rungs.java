class Solution {
    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int current = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += (rungs[i] - current - 1) / dist;
            current = rungs[i];
        }
        return count;
    }
}