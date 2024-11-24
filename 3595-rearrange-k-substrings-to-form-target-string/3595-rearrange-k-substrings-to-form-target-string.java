class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length();
        int part = n / k;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += part) {
            String current = s.substring(i, i + part);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }
        for (int i = 0; i < n; i += part) {
            String current = t.substring(i, i + part);
            if (map.getOrDefault(current, 0) > 0) map.put(current, map.getOrDefault(current, 0) -1);
            else return false;
        }
        return true;
    }
}