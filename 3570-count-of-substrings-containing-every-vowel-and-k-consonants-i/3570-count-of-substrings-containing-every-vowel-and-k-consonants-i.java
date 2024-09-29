class Solution {
    public int countOfSubstrings(String word, int k) {
        int n = word.length();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                char current = word.charAt(j);
                if (isVowel(current)) {
                    map.put(current, map.getOrDefault(current, 0) + 1);
                }   
                else count++;
                if (compute(map) == true && count == k) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private boolean compute(HashMap<Character, Integer> map) {
        int count = 0;
        if (map.getOrDefault('a' , 0) > 0) count++;
        if (map.getOrDefault('e' , 0) > 0) count++;
        if (map.getOrDefault('i' , 0) > 0) count++;
        if (map.getOrDefault('o' , 0) > 0) count++;
        if (map.getOrDefault('u' , 0) > 0) count++;
        return count == 5;        
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}