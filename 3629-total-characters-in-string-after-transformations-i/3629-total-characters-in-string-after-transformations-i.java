class Solution {
    static int MOD = (int)(1e9 + 7);
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        int res = 0;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;
        while (t-->0) {
            int zCount = freq[25];
            for (int i = 24; i >= 0; i--) {
                freq[i + 1] = freq[i];
                freq[i] = 0;
            }
            if (zCount > 0) {
                freq[0] = (freq[0] + zCount) % MOD;
                freq[1] = (freq[1] + zCount) % MOD;
            }
        }
        for (int ele : freq) res = (res + ele) % MOD;
        return res;
    }
}