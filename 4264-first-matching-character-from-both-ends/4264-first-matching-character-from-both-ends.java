class Solution {
    public int firstMatchingIndex(String s) {
        int n = s.length();
        for (int i = 0; i <= n / 2; i++) {
            if (s.charAt(i) == s.charAt(n - 1 - i)) return i;
        }
        return -1;
    }
}