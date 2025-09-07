class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        } 
        int res = 0;
        for (int i = 1; i < 25; i++) {
            if (freq[i] == 0) continue;
            res++;
            freq[i + 1] += freq[i];
        }
        if (freq[25] > 0) 
            res++;
        return res; 
    }
}