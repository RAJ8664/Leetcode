class Solution {
    public String reverseWords(String s) {
        s += " ";
        int n = s.length();
        ArrayList<String> res = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                res.add(current.toString());
                current = new StringBuilder();
            } else 
                current.append(ch);
        }
        for (int i = 1; i < res.size(); i++) {
            if (getCount(res.get(i)) == getCount(res.get(0))) {
                StringBuilder newS = new StringBuilder(res.get(i));
                res.set(i, newS.reverse().toString());
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            if (i != res.size() - 1) {
                ans.append(res.get(i));
                ans.append(' ');
            } else
                ans.append(res.get(i));
        }
        return ans.toString();
    }
    private int getCount(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                count++;
        }
        return count;
    }
}