class Solution {
    public String reverseVowels(String s) {
        int n = s.length();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (isVowel(current)) res.add(i);
        }
        Collections.reverse(res);
        int k = 0;
        String ans = "";
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (isVowel(current)) ans += s.charAt(res.get(k++));
            else ans += current;
        }
        return ans;
    }
    private boolean isVowel(char current) {
        if (current == 'a' || current == 'A') return true;
        if (current == 'e' || current == 'E') return true;
        if (current == 'i' || current == 'I') return true;
        if (current == 'o' || current == 'O') return true;
        if (current == 'u' || current == 'U') return true;
        return false;
    }
}