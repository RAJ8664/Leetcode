class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u') 
                return true;
        }    
        return false; 
    }
}