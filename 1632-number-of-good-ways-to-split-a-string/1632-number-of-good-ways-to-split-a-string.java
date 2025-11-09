class Solution {
    public int numSplits(String s) {
        int n = s.length();
        if (n == 1)
            return 0;
        int pref[] = new int[n];
        int suff[] = new int[n];
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(s.charAt(i));
            pref[i] = set.size();
        }
        set.clear();
        for (int i = n - 1; i >= 0; i--) {
            set.add(s.charAt(i));
            suff[i] = set.size();
        }
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (pref[i] == suff[i + 1])
                res++;
        }
        return res;
    }
}