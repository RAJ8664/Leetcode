class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        long res = 0;
        int idx = 0;
        int vis[] = new int[values.length];
        while (true) {
            if (vis[idx] == 1) break;
            vis[idx] = 1;
            if (instructions[idx].equals("add")) res += values[idx++];
            else idx += values[idx];
            if (idx >= values.length || idx < 0) break;
        }
        return res;
    }
}