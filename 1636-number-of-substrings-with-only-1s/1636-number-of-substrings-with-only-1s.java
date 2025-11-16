class Solution {
    private int mod = (int)(1e9 + 7);
    public int numSub(String s) {
        int n = s.length();
        int prev = -1, current = -1;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (prev == -1) {
                    prev = i;
                    current = i;
                } else {
                    current = i;
                }
            } else {
                if (prev == -1 || current == -1) continue;
                int len = (current - prev + 1);
                res = (res + (len * 1L * (len + 1) / 2)) % mod; 
                prev = -1; current = -1;
            }
        }
        if (prev != -1 && current != -1) {
            int len = (current - prev + 1);
            res = (res + (len * 1L * (len + 1) / 2)) % mod;
        }
        return (int)(res);
    }
}