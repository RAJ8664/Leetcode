class Solution {
    HashMap<String, ArrayList<String>> mp = new HashMap<>();
    HashMap<String, Boolean> dp = new HashMap<>();
    boolean solve(int ind, String current, String prev, int n) {
        if (n == 1) return true;
        if (ind == n - 1) {
            if (dp.containsKey(current)) 
                return dp.get(current);
            boolean ans = solve(0, "", current, n - 1);
            dp.put(current, ans);
            return ans;
        }
        String tmp = prev.substring(ind, ind + 2);
        if (!mp.containsKey(tmp)) 
            return false;
        for (String x : mp.get(tmp)) {
            if (solve(ind + 1, current + x, prev, n))
                return true;
        }
        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int n = bottom.length();
        for (String s : allowed) {
            String key = s.substring(0, 2);
            String val = s.substring(2);
            mp.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
        }
        return solve(0, "", bottom, n);
    }
}
