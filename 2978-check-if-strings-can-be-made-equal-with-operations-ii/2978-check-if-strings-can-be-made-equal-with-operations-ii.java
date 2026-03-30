class Solution {
    private int firstOdd[];
    private int secondOdd[];
    private int firstEven[];
    private int secondEven[];

    public boolean checkStrings(String s, String t) {
        int n = s.length(), m = t.length();
 
        firstOdd = new int[26];
        secondOdd = new int[26];
        firstEven = new int[26];
        secondEven = new int[26];

        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (i % 2 == 0)
                firstEven[current - 'a']++;
            else
                firstOdd[current - 'a']++;
        }

        for (int i = 0; i < m; i++) {
            char current = t.charAt(i);
            if (i % 2 == 0)
                secondEven[current - 'a']++;
            else
                secondOdd[current - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (firstOdd[i] != secondOdd[i] || firstEven[i] != secondEven[i])
                return false;
        }

        return true;
    }
}