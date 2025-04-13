class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        if (n == 1) return s;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) freq[s.charAt(i) - 'a']++;
        StringBuilder res = new StringBuilder();
        boolean flag = false;
        char toPlace = 'x';
        for (int i = 0; i < 25; i++) {
            if (freq[i] % 2 == 1) {
                char current = (char)('a' + i);
                flag = true;
                toPlace = current;
            }
        }
        for (int i = 0; i < 26; i++) {
            int times = freq[i] / 2;
            char current = (char)('a' + i);
            for (int j = 0; j < times; j++) res.append(current);
            freq[i] -= times;
        }
        if (flag == true) {
            freq[toPlace - 'a']--;
            res.append(toPlace);
        }
        for (int i = 25; i >= 0; i--) {
            int times = freq[i];
            char current = (char)('a' + i);
            for (int j = 0; j < times; j++) res.append(current);
            freq[i] -= times;
        }
        return res.toString();
    }
}