class Solution {
    public long maxRunTime(int n, int[] batteries) {
        Arrays.sort(batteries);
        long sum = 0;
        for (int a: batteries)
            sum += a;
        int low = 0, high = batteries.length;
        while (batteries[high - 1 - low] > sum / (n - low))
            sum -= batteries[high - 1 - low++];
        return sum / (n - low);
    }
}