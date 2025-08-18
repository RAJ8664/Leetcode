class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (s1.charAt(i) != s2.charAt(i)) 
                arr.add(i);
        n = arr.size();
        if (n % 2 == 1) 
            return -1;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, (int)(1e9));
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = x + dp[i + 1];
            if (i < n - 1) 
                dp[i] = Math.min(dp[i], 2 * (arr.get(i + 1) - arr.get(i)) + dp[i + 2]);
        } 
        return dp[0] / 2;
    }
}