class Solution {
    private int dp[][][];
    private int xorPref[];
    HashMap<Integer, Long> dp1 = new HashMap<>();
    HashMap<Integer, Long> dp2 = new HashMap<>();
    private int mod = (int)(1e9 + 7);

    public int alternatingXOR(int[] nums, int target1, int target2) {
        int n = nums.length;
        /*

            dp = new int[n + 1][n + 1][3];
            for (int current[][] : dp)
            for (int current1[] : current)
                Arrays.fill(current1, -1);

        */
        xorPref = new int[n + 1];
        xorPref[0] = nums[0];
        for (int i = 1; i < n; i++)
            xorPref[i] = xorPref[i - 1] ^ nums[i];

        long res = 0, curr1 = 0, curr2 = 0;
        dp1 = new HashMap<>();
        dp2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long current1 = dp2.getOrDefault(xorPref[i] ^ target1, 0L);
            if (xorPref[i] == target1)
                current1 = (current1 + 1) % mod;

            long current2 = dp1.getOrDefault(xorPref[i] ^ target2, 0L);

            dp1.put(xorPref[i], dp1.getOrDefault(xorPref[i], 0L) + current1 % mod);
            dp2.put(xorPref[i], dp2.getOrDefault(xorPref[i], 0L) + current2 % mod);

            curr1 = current1;
            curr2 = current2;
        }
        // int res = solve(0, -1, 1, target1, target2, nums);
        return (int)((curr1 + curr2) % mod);
    }

    private int solve(int ind, int prev, int curr, int target1, int target2, int arr[]) {
        if (ind == arr.length - 1) {
            int total = xorPref[arr.length - 1];
            if (prev >= 0)
                total ^= xorPref[prev];
            if (curr == 1) {
                if (total == target1)
                    return 1;
                return 0;
            }
            if (curr == 2) {
                if (total == target2)
                    return 1;
                return 0;
            }
        }
        if (dp[ind][prev + 1][curr] != -1)
            return dp[ind][prev + 1][curr];
        int op1 = 0, op2 = 0;
        op1 = solve(ind + 1, prev, curr, target1, target2, arr);
        int total = xorPref[ind];
        if (prev >= 0)
            total ^= xorPref[prev];
        if (curr == 1) {
            if (total == target1)
                op2 = solve(ind + 1, ind, 2, target1, target2, arr);
        }
        if (curr == 2) {
            if (total == target2)
                op2 = solve(ind + 1, ind, 1, target1, target2, arr);
        }
        return dp[ind][prev + 1][curr] = (op1 + op2) % mod;
    }
}