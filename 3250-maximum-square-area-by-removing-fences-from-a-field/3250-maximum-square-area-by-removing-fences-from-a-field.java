class Solution {
    private int mod = (int)(1e9 + 7);
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        if (n == m) {
            long len = n - 1;
            return (int)((len * len) % mod);
        }
        HashSet<Integer> hDiff = new HashSet<>();
        List<Integer> hList = new ArrayList<>();
        List<Integer> vList = new ArrayList<>();
        hList.add(1); vList.add(1);
        for (int x : hFences) hList.add(x);
        for (int x : vFences) vList.add(x);
        Collections.sort(hList); Collections.sort(vList);
        hList.add(m); vList.add(n);
        for (int i = 0; i < hList.size(); i++) {
            for (int j = i + 1; j < hList.size(); j++) {
                hDiff.add(hList.get(j) - hList.get(i));
            }
        }
        int maxi = -1;
        for (int i = 0; i < vList.size(); i++) {
            for (int j = i + 1; j < vList.size(); j++) {
                int current = vList.get(j) - vList.get(i);
                if (hDiff.contains(current)) {
                    maxi = Math.max(maxi, current);
                }
            }
        }
        if (maxi == -1) return -1;
        long area = maxi * 1L * maxi;
        return (int)(area % mod);
    }
}