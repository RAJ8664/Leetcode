class Solution {
    private long dp[][];
    public long maxSum(int[][] grid, int[] limits, int k) {
        int n = grid.length, m = grid[0].length;
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> r = new ArrayList<>();
            for (int j = 0; j < m; j++) r.add(grid[i][j]);
            Collections.sort(r);
            Collections.reverse(r);
            ArrayList<Integer> r1 = new ArrayList<>();
            for (int ele : r) if (r1.size() < limits[i]) r1.add(ele);
            elements.add(new ArrayList<>(r1));
        }
        for (ArrayList<Integer> x : elements)  for (int ele : x) arr.add(ele);
        Collections.sort(arr);
        Collections.reverse(arr);
        long ans = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (k == 0) break;
            ans += arr.get(i);
            k--;
        }
        return ans;
    }
    private long solve(int ind, int k, ArrayList<Integer> arr) {
        if (ind >= arr.size()) return 0;
        if (dp[ind][k] != -1) return dp[ind][k];
        long op1 = Integer.MIN_VALUE / 10, op2 = Integer.MIN_VALUE / 10;
        if (k > 0) op1 = arr.get(ind) + solve(ind + 1, k - 1, arr);
        op2 = solve(ind + 1, k, arr);
        return dp[ind][k] = Math.max(op1, op2);
    }
}