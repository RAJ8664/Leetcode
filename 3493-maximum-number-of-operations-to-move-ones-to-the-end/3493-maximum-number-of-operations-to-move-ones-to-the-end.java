class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int count = 0, cnt = 0;
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '1') 
                cnt++;
            if (s.charAt(i) == '1' && s.charAt(i + 1) == '0') 
                count += cnt;
        }
        return count;
    }
}