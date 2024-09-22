class Solution {
    public long validSubstringCount(String s1, String s2) {
        long ans = 0;
        int[] first = new int[26];
        for(int i = 0; i < s2.length(); i++) first[s2.charAt(i)-'a']++;
        int n = s1.length();
        int[] second = new int[26];
        for(int i = 0, j = 0; i < n; i++) {
            while(j < n && !check(first, second)) {
                second[s1.charAt(j)-'a']++;
                j++;
            }
            if(check(first, second))
                ans += n - j + 1;
            second[s1.charAt(i) - 'a']--;
        }
        return ans;
    }
    
    private boolean check(int[] a, int[] b) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] > b[i]) return false;
        }
        return true;
    }
}