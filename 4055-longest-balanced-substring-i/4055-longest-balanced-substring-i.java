class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int freq[] = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                int flag = 1;
                int maxi = 0;
                for (int ele : freq) 
                    maxi = Math.max(maxi, ele);
                for (int ele : freq) {
                    if (ele > 0 && ele != maxi)
                        flag = 0; 
                }
                if (flag == 1)
                    res = Math.max(res, j - i + 1);
            }
        }
        return res; 
    }
}