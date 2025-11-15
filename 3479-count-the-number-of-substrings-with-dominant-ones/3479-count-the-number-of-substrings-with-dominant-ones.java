class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] pref = new int[n + 1];
        pref[0] = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0 || (i > 0 && s.charAt(i - 1) == '0')) {
                pref[i + 1] = i;
            } else {
                pref[i + 1] = pref[i];
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int cnt0 = s.charAt(i - 1) == '0' ? 1 : 0;
            int j = i;
            while (j > 0 && cnt0 * cnt0 <= n) {
                int cnt1 = (i - pref[j]) - cnt0;
                if (cnt0 * cnt0 <= cnt1) {
                    res += Math.min(j - pref[j], cnt1 - cnt0 * cnt0 + 1);
                }
                j = pref[j];
                cnt0++;
            }
        }
        return res;
    }
}