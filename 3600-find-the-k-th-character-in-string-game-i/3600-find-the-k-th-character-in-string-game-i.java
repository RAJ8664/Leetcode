class Solution {
    public char kthCharacter(int k) {
        String res = "0";
        while (true) {
            if (res.length() >= k) break;
            String to_append = "";
            for (int i = 0; i < res.length(); i++) {
                int current = res.charAt(i) - '0';
                current++;
                current = current % 26;
                to_append += current;
            }
            res += to_append;
        }
        return (char)(res.charAt(k - 1) - '0' + 'a');
    }
}