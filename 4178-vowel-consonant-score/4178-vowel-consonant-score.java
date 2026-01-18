class Solution {
    public int vowelConsonantScore(String s) {
        int n = s.length();
        int c = 0;
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        int extra = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == ' ' || !Character.isAlphabetic(current)) {
                extra++;
                continue;
            }
            if (!vowels.contains(current))
                c += 1;
        }
        if (c > 0)
            return (s.length() - c - extra) / c;
        return 0;
    }
}