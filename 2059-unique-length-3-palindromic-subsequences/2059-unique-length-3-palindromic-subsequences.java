class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length(), res = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);
            if (first != -1 && last != -1 && first < last) {
                Set<Character> temp = new HashSet<>();
                for (int i = first + 1; i < last; i++) temp.add(s.charAt(i));
                res += temp.size();
            }
        }
        return res;
    }
}