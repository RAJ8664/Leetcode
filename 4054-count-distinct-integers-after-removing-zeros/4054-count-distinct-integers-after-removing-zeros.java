class Solution {
    public long countDistinct(long n) {
        long res = 0;
        String s = Long.toString(n);
        int run = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '0') break;
            int dig = current - '0';
            res += (dig * Math.pow(9, run--));
        }
        while (run > 0)
            res += (Math.pow(9, run--));
        return res;
    }
}