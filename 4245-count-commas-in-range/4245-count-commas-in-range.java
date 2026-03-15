class Solution {
    public int countCommas(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String curr = Integer.toString(i);
            if (curr.length() <= 3) continue; 
            if (curr.length() % 3 == 0) {
                res += curr.length() / 3 - 1;
            }
            else if (curr.length() >= 4) {
                res += curr.length() / 3;
            }
        }
        return res;
    }
}