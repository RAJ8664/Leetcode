class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n=letters.length;
        for(Character ch:letters){
            if(target<ch){
                return ch;
            }
        }
        return letters[0];
    }
}