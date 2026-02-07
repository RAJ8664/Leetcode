class Solution {
    private int suff_a[];
    private int pref_b[];
    public int minimumDeletions(String s) {
        int n = s.length();
        int min = Integer.MAX_VALUE;
        pref_b = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'b') count++;
            pref_b[i] = count;
        }
        suff_a = new int[n];
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') 
                count++;
            suff_a[i] = count;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                min = Math.min(min, pref_b[i]);
                break;
            }
        }
        for (int i = 0; i < n; i++) 
            min = Math.min(min, pref_b[i] + suff_a[i] - 1);
        return min;
    }
}