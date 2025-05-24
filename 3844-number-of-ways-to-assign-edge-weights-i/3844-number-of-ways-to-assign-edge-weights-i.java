class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int depth[];
    private long[] factorials;
    private long[] invFactorials;
    private int mod = (int)(1e9 + 7);
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        adj = new ArrayList<>();
        depth = new int[n + 1];
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        precompFacts();
        dfs(1, 0);
        int maxi_depth = 0;
        for (int ele : depth) maxi_depth = Math.max(maxi_depth, ele);
        return count_ways(maxi_depth);
    }
    private void dfs(int u, int par) {
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