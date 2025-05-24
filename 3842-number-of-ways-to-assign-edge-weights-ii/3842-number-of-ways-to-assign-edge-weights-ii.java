class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int depth[];
    private long[] factorials;
    private long[] invFactorials;
    private int dp[][];
    private int mod = (int)(1e9 + 7);
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new ArrayList<>();
        depth = new int[n + 1];
        dp = new int[n + 1][19];
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        precompFacts();
        dfs(1, 0);
        int ans[] = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            ans[i] = count_ways(depth[u] + depth[v] - 2 * depth[lca(u, v)]);
        }
        return ans;
    }
    private void dfs(int u, int par) {
        dp[u][0] = par;
        for (int i = 1; i < 19; i++) dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int v : adj.get(u)) {
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
            }
        }
    }
    private int count_ways(int n) {
        long ans = 0;
        for (int k = 1; k <= n; k += 2) ans = add(ans, nCk(n, k));
        return (int)(ans);
    }
    private int find_kth_parent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1) u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }
    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = find_kth_parent(v, diff);
        if (u == v) return u;
        for (int i = 18; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];
    }
    private long mul(long a, long b) {return (long) ((long) ((a % mod) * 1L * (b % mod)) % mod);}
    private long add(long a, long b) {a += b; if (a >= mod) a-= mod; return a;}
    private long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }
    private void precompFacts() {
        factorials = new long[(int)(1e5 + 1)];
        invFactorials = new long[(int)(1e5 + 1)];
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }
    private long exp(long base, long exp) {
        if (exp == 0) return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0) return mul(half, half);
        return mul(half, mul(half, base));
    }
}