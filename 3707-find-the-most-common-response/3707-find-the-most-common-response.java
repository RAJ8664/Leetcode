class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        int n = responses.size();
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (List<String> curr : responses) {
            HashSet<String> set = new HashSet<>();
            for (String x : curr) {
                if (!set.contains(x)) {
                    set.add(x);
                    map.put(x, map.getOrDefault(x, 0) + 1);
                }
            }
        }
        int maxi = 0;
        for (Map.Entry<String, Integer> curr : map.entrySet()) {
            String key = curr.getKey();
            int val = curr.getValue();
            maxi = Math.max(maxi, val);
        }
        for (Map.Entry<String, Integer> curr : map.entrySet()) {
            String key = curr.getKey();
            int val = curr.getValue();
            if (val == maxi) res.add(key);
        }
        Collections.sort(res);
        return res.get(0);
    }
}