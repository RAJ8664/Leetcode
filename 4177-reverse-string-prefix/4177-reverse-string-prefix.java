class Solution {
    public String reversePrefix(String s, int k) {
        int n = s.length();
        StringBuilder temp = new StringBuilder(s.substring(0, k));
        StringBuilder res = new StringBuilder(temp.reverse().toString());
        res.append(s.substring(k, n));
        return res.toString();

    }
}