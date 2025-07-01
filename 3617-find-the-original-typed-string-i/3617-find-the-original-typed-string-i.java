class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();
        Set<String> set = new HashSet<>();
        set.add(word); 
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && word.charAt(j) == word.charAt(i)) j++;
            int length = j - i;
            if (length > 1) {
                for (int k = 1; k < length; k++) {
                    String res = word.substring(0, i) + word.substring(i, i + k) + word.substring(j);
                    set.add(res);
                }
            }
            i = j;
        }
        return set.size();
    }
}