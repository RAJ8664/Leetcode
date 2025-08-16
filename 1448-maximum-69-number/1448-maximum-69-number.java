class Solution {
    public int maximum69Number (int num) {
        String s = "" + num;
        int res = 0, flag = 1;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '6' && flag == 1) {
                res = res * 10 + 9;
                flag = 0;
            }
            else 
                res = res * 10 + current - '0';
        }
        return res;
    }
}