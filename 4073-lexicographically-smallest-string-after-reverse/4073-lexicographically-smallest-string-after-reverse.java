class Solution {
    public String lexSmallest(String s) {
        int n = s.length();
        ArrayList<String> res = new ArrayList<>();
        for (int k = 1; k <= n; k++) {
            StringBuilder first = new StringBuilder(s.substring(0, k));
            String op1 = first.reverse().toString() + s.substring(k, n);
            
            StringBuilder second = new StringBuilder(s.substring(n - k, n));
            String op2 = s.substring(0, n - k) + second.reverse().toString();
            
            res.add(op1); res.add(op2);
        }
        Collections.sort(res);
        return res.get(0);
    }
}