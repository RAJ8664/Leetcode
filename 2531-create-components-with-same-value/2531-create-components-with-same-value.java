class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int count;
    private int val[];
    private int dp[];
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        val = new int[n];
        dp = new int[n + 1];
        int start = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            sum += val[i];
            start = Math.max(start, val[i]);
        }
        
        count = 0;
        int maxi = 0;

        ArrayList<Integer> divs = new ArrayList<>();
        divs = getDivs(sum);

        for (int x = 0; x < divs.size(); x++) {
            int i = divs.get(x);
            dp = new int[n + 1];
            dfs(0, -1, i);
            if (dp[0] == 0) {
                maxi = Math.max(maxi, count - 1);
            }
            count = 0;
        }
        return maxi;
    }
    private void dfs(int u, int par, int req) {
        if (adj.get(u).size() == 1 && u != 0) {
            if (val[u] == req) {
                dp[u] = 0;
                count++;
            }
            else if (val[u] > req) {
                dp[u] = (int)(1e3);
            }
            else dp[u] = val[u];
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u, req);
            }
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dp[u] += dp[v];
            }
        }
        dp[u] += val[u];
        if (dp[u] == req) {
            count++;
            dp[u] = 0;
        }
        else if (dp[u] > req) {
            dp[u] = (int)(1e3);
        }
    }
    private ArrayList<Integer> getDivs(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (n / i != i) {
                    res.add(n / i);
                } 
            }
        }
        return res;
    }
}