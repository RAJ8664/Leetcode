class Solution {
    private int mod = (int)(1e9 + 7);
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        for (int i = 1; i < n; i++) if (complexity[i] <= complexity[0]) return 0;
        long ans = 1;
        for (int i = n - 1; i >= 1; i--) {
            ans = (ans * i) % mod;
        }
        return (int)(ans);
    }
}