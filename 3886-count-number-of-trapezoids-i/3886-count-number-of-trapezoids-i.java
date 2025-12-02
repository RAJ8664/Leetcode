class Solution {
    private int mod = (int)(1e9 + 7);
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(points[i][1], map.getOrDefault(points[i][1], 0) + 1);
        long sum = 0, res = 0;
        for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
            int t = curr.getValue();
            long current = (long)(t * 1L * (t - 1) / 2);
            res = (res + sum * 1L * current) % mod;
            sum = (sum + current) % mod;
        }
        return (int)(res); 
    }
}