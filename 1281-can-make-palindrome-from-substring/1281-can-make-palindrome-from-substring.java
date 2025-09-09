class Solution {
    private int pref[][];
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        pref = new int[n][26];
        
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            for (int j = 0; j < 26; j++) 
                if (i - 1 >= 0) 
                    pref[i][j] = pref[i - 1][j];
            pref[i][current - 'a']++;
        }

        List<Boolean> res = new ArrayList<>();
        for (int query[] : queries) {
            int l = query[0], r = query[1], k = query[2];
            int countOdd = 0;
            for (int i = 0; i < 26; i++) {
                int total = pref[r][i];
                if (l - 1 >= 0) 
                    total -= pref[l - 1][i];
                if (total % 2 == 1) 
                    countOdd++;
            }
            if (2 * k >= countOdd - 1) 
                res.add(true);
            else 
                res.add(false);
        }
        return res;
    }
}