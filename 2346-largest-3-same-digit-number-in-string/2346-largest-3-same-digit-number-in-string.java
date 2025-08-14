class Solution {
    public String largestGoodInteger(String s) {
        int n = s.length();
        String res = ""; 
        boolean found = false;
        for (int i = 0; i < n - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == s.charAt(i + 2)) {
                String current = s.charAt(i) + "" + s.charAt(i + 1) + ""+ s.charAt(i + 2);
                if (current.compareTo(res) > 0) {
                    res = current;
                } 
            }
        }
        return res;
    }
}