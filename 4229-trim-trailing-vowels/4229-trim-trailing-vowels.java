class Solution {
    public String trimTrailingVowels(String s) {
        int n = s.length();
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        StringBuilder res = new StringBuilder();
        boolean flag = true;
        int idx = n - 1;
        while (idx >= 0) {
            if (!vowels.contains(s.charAt(idx))) 
                break;
            else
                idx--;
        }
        for (int i = 0; i <= idx; i++)
            res.append(s.charAt(i));
        return res.toString();
            
    }
}