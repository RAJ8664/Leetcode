class Solution {
    private ArrayList<String> ans;
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        ans = new ArrayList<>();
        for (int i = minSize; i <= maxSize; i++) getSubstringsOfLengthK(s, i, maxLetters);
        HashMap<String, Integer> map = new HashMap<>();
        for (String x : ans) map.put(x, map.getOrDefault(x, 0) + 1);
        int maxi = 0;
        for (int current : map.values()) maxi = Math.max(maxi, current);
        return maxi;
    }
    private ArrayList<String> getSubstringsOfLengthK(String s, int k, int maxLetters) {
        int n = s.length();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i + k <= n) {
                String current = s.substring(i, i + k);
                HashSet<Character> set = new HashSet<>();
                for (int j = 0; j < current.length(); j++) set.add(current.charAt(j));
                if (set.size() <= maxLetters) ans.add(current);
            }
        }
        return res;
    }
}