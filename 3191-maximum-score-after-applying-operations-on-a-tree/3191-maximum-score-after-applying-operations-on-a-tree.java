class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int dp[][];
    private int cost[];
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        cost = new int[n];
        for (int i = 0; i < n; i++) 
            cost[i] = values[i];
        long total = 0;
        for (int ele : cost) 
            total += ele;
        return total - dfs(0, -1);
    }
    private long dfs(int u, int par) {
        if (adj.get(u).size() == 1 && u != 0) {
            return cost[u] * 1L;
        }

        long current = 0;
        for (int v : adj.get(u)) {
            if (v != par) {
                current += dfs(v, u);
            }
        }
        return Math.min(current, cost[u]);
    }
}