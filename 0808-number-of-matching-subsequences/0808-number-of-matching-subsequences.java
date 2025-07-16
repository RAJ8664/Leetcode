class Solution {
    private HashMap<Character, TreeSet<Integer>> map;
    public int numMatchingSubseq(String s, String[] words) {
        map = new HashMap<>();
        preProcess(s);
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (check(words[i])) 
                count++;
        }
        return count;
    }
    private boolean check(String s) {
        int n = s.length();
        int ind = -1;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (!map.containsKey(current)) 
                return false;
            TreeSet<Integer> currChar = map.get(current);
            Integer next = currChar.higher(ind);
            if (next == null) 
                return false;
            ind = next;
        }
        return true;
    }
    private void preProcess(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (!map.containsKey(current))
                map.put(current, new TreeSet<>());
            map.get(current).add(i);
        }
    }
}