class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = minSize; i <= maxSize; i++) {
            ArrayList<String> temp = new ArrayList<>();
            temp = getSubstringsOfLengthK(s, i);
            for (String x : temp) res.add(x);
        }
        ArrayList<String> ans = new ArrayList<>();
        for (String x : res) {
            HashSet<Character> set = new HashSet<>();
            for (int i = 0; i < x.length(); i++) set.add(x.charAt(i));
            if (set.size() <= maxLetters) ans.add(x);
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String x : ans) map.put(x, map.getOrDefault(x, 0) + 1);
        int maxi = 0;
        for (Map.Entry<String, Integer> curr : map.entrySet()) {
            String key = curr.getKey();
            int val = curr.getValue();
            maxi = Math.max(maxi, val);
        }
        return maxi;
    }
    private ArrayList<String> getSubstringsOfLengthK(String s, int k) {
        int n = s.length();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i + k <= n) {
                res.add(s.substring(i, i + k));
            }
        }
        return res;
    }
}