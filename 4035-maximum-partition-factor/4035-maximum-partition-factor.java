class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int vis[];
    private int color[];
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if (n <= 2)
            return 0;
        int low = 0, high = (int)(1e9), ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, points)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean ok(int target, int points[][]) {
        int n = points.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                if (dist < target) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        boolean res = true;
        vis = new int[n + 1];
        color = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                res &= dfs(i, -1, 1);
            }
        }
        return res;
    }
    private boolean dfs(int u, int par, int currentColor) {
        vis[u] = 1;
        color[u] = currentColor;
        boolean ans = true;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                ans &= dfs(v, u, currentColor ^ 1);
            } else {
                if (color[v] == currentColor) 
                    ans = false;
            }
        }
        return ans;
    }
}

