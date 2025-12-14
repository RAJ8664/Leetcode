class Solution {
    private int mod = (int)(1e9 + 7);
    public int numberOfWays(String s) {
        int n = s.length();
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'S')
                idx.add(i);
        } 
        if (idx.size() % 2 == 1 || idx.size() == 0)
            return 0;
        long res = 1;
        for (int i = 2; i < idx.size(); i += 2) {
            res = (res * (idx.get(i) - idx.get(i - 1))) % mod;
        }
        return (int)(res);
    }
}