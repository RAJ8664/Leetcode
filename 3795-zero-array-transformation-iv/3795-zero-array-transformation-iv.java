class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        boolean flag = false;
        for (int ele : nums) {
            if (ele != 0) flag = true;
        }
        if (flag == false) return 0;
        int low = 0, high = queries.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, queries, nums)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        if (ans == -1) return ans;
        return ans + 1;
    }
    private boolean ok(int mid, int queries[][], int nums[]) {
        int n = nums.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(new ArrayList<>());
        for (int i = 0; i <= mid; i++) {
            int u = queries[i][0], v = queries[i][1], val = queries[i][2];
            for (int j = u; j <= v; j++) res.get(j).add(val);
        }
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] > 0 && res.get(i).size() == 0) return false;
            else {
                if (solve(arr[i], res.get(i)) == false) return false;
            }
        }
        return true;
    }
    private boolean solve(int target, ArrayList<Integer> arr) {
        int n = arr.size();
        int dp[][] = new int[n + 1][target + 1];
        for (int current[] : dp) Arrays.fill(current, -1);
        int res = target_sum(0, arr, dp, target);
        return res == 1;
    }
    private int target_sum(int ind, ArrayList<Integer> arr, int dp[][], int target) {
        if (ind == arr.size() - 1) {
            if (target == 0 || target == arr.get(arr.size() - 1)) return 1;
            return 0;
        }
        if (dp[ind][target] != -1) return dp[ind][target];
        int op1 = target_sum(ind + 1, arr, dp, target);
        int op2 = 0;
        if (target >= arr.get(ind)) {
            op2 = target_sum(ind + 1, arr, dp, target - arr.get(ind));
        }
        return dp[ind][target] = op1 | op2; 
    }
}