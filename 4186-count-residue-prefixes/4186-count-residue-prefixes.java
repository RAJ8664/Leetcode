class Solution {
    public int residuePrefixes(String s) {
        int n = s.length();
        int count = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(s.charAt(i));
            if (set.size() == (i + 1) % 3)
                count++;
        }
        return count;
    }
}