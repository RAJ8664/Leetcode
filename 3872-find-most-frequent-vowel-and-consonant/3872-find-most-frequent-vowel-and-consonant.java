class Solution {
    public int maxFreqSum(String s) {
        int n = s.length();
        int freq[] = new int[26];
        int maxiV = 0, maxiC = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            freq[s.charAt(i) - 'a']++;
            if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u')
                maxiV = Math.max(maxiV, freq[current - 'a']);
            else 
                maxiC = Math.max(maxiC, freq[current - 'a']);            
        } 
        return maxiV + maxiC;
    }
}