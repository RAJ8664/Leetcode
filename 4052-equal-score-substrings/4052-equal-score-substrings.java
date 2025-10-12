class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int pref[] = new int[n];
        int suff[] = new int[n];
        pref[0] = s.charAt(0) - 'a' + 1;
        suff[n - 1] = s.charAt(n - 1) - 'a' + 1;
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + s.charAt(i) - 'a' + 1;
        for (int i = n - 2; i >= 0; i--)
            suff[i] = suff[i + 1] + s.charAt(i) - 'a' + 1;
        for (int i = 0; i < n - 1; i++) {
            if (pref[i] == suff[i + 1])
                return true; 
        }
        return false;
    }
}
