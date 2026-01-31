class Solution {
    public String reverseByType(String s) {
        int n = s.length();
        ArrayList<Character> letter = new ArrayList<>();
        ArrayList<Character> special = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(s.charAt(i)))
                letter.add(s.charAt(i));
            else
                special.add(s.charAt(i));
        }
        int sp = special.size() - 1, le = letter.size() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(s.charAt(i)))
                sb.append(letter.get(le--));
            else
                sb.append(special.get(sp--));
        }
        return sb.toString();
    }
}