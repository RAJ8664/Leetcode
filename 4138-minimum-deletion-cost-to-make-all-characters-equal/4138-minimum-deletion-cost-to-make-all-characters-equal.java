class Solution {
    private long price[];
    public long minCost(String s, int[] cost) {
        int n = s.length();
        price = new long[26];
        for (int i = 0; i < n; i++)
            price[s.charAt(i) - 'a'] += cost[i] * 1L;
        long totalCost = 0;
        for (int i = 0; i < 26; i++)
            totalCost += price[i];
        boolean isSame = true;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(0))
                isSame = false;
        }
        if (isSame)
            return 0;
        long mini = Long.MAX_VALUE;
        for (int i = 0; i < 26; i++)
            mini = Math.min(mini, totalCost - price[i]);
        return mini;
    }
}