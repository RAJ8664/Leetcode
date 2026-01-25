class Solution {
    private ArrayList<ArrayList<Integer >> adj;
    private int depth[];
    private int dp[][];

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        adj = new ArrayList<>();
        depth = new int[n + 1];
        dp = new int[n + 1][19];

        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0] + 1, v = edge[1] + 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(1, 0);

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int dx = depth[i] + depth[x + 1] - 2 * depth[Lca(i, x + 1)];
            int dy = depth[i] + depth[y + 1] - 2 * depth[Lca(i, y + 1)];
            int dz = depth[i] + depth[z + 1] - 2 * depth[Lca(i, z + 1)];
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(dx);
            temp.add(dy);
            temp.add(dz);
            if (isPythagorean(temp))
                count++;
        }
        return count;
    }

    private void dfs(int u, int par) {
        dp[u][0] = par;
        for (int i = 1; i < 19; i++)
            dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int v : adj.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
            }
        }
    }

    private int kthParent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1)
                u = dp[u][count];
            k /= 2;
            count++;
        }
        return u;
    }

    private int Lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = kthParent(v, diff);
        if (u == v)
            return u;

        for (int i = 18; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];
    }

    private boolean isPythagorean(ArrayList<Integer> arr) {
        Collections.sort(arr);
        int a = arr.get(0), b = arr.get(1), c = arr.get(2);
        return (a * a + b * b == c * c);
    }
}