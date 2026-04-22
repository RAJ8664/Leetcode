class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String x: queries) {
            boolean flag = false;
            for (int i = 0; i < dictionary.length; i++) {
                if (ok(x, dictionary[i])) {
                    flag = true;
                    break;
                }
            }
            if (flag) 
                res.add(x);
        }
        return res;
    }

    private boolean ok(String s, String t) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i))  
                count += 1;
        }
        return count <= 2;
    }

}