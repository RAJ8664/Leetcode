class Solution {
    public long numOfSubsequences(String s) {
        int n = s.length();
        long pref[] = new long[n + 1];
        long suff[] = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (s.charAt(i) == 'L' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            suff[i] += suff[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
        }

        long l = 0, c = 0, t = 0, placeC = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == 'C') {
                c += (pref[i] * suff[i + 1]);
                l += ((pref[i] + 1) * (suff[i + 1]));
                t += (pref[i] * (suff[i + 1] + 1));
            }
            else 
                placeC = Math.max(placeC, pref[i] * suff[i]);
        }
        c += placeC;
        return Math.max(Math.max(l, c), t);
    }
}