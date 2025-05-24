class Solution {
    public int maxSubstrings(String s) {
        int n = s.length();
        int occurred[] = new int[26];
        Arrays.fill(occurred, -1);
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            boolean isSeen = occurred[ch - 'a'] != -1;
            if (isSeen) {
                if (occurred[ch - 'a'] - i + 1 >= 4) {
                    res++;
                    Arrays.fill(occurred, -1);
                }
            }
            else occurred[ch - 'a'] = i;
        }
        return res;
    }
}