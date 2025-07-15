class Solution {
    public boolean isValid(String word) {
        int n = word.length();
        if (n < 3)
            return false;

        word = word.toLowerCase();
        int dc = 0, vc = 0, cc = 0;
        for (int i = 0; i < n; i++) {
            char current = word.charAt(i);
            if (Character.isDigit(current))
                dc++;
            else if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u')
                vc++;
            else if (Character.isLetter(current) && (current != 'a' && current != 'e' && current != 'i' && current != 'o' && current != 'u'))
                cc++;
        }
        if (dc + cc + vc != n || vc == 0 || cc == 0)
            return false;
        return true;

    }
}