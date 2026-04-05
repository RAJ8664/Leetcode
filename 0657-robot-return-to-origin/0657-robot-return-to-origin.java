class Solution {
    public boolean judgeCircle(String moves) {
        int n = moves.length();
        int u = 0, d = 0, l = 0, r = 0;
        for (int i = 0; i < n; i++){
            if (moves.charAt(i) == 'U')
                u++;
            else if (moves.charAt(i) == 'D')
                d++;
            else if (moves.charAt(i) == 'L')
                l++;
            else if (moves.charAt(i)=='R')
                r++;
        }
        if (u == d && l == r)
            return true;
        return false;
    }
}