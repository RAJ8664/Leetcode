class Solution {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length, m = 0;
        for (int ele : x) m = Math.max(m, ele);
        int res[] = new int[m + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int ele : x) set.add(ele);
        if (set.size() < 3) return -1;
        for (int i = 0; i < n; i++) {
            res[x[i]] = Math.max(res[x[i]], y[i]);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : set) ans.add(res[ele]);
        Collections.sort(ans);
        return ans.get(ans.size() - 1) + ans.get(ans.size() - 2) + ans.get(ans.size() - 3);
    }
}