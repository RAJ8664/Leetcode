class Solution {
    private List<Integer>[] g;
    private int[] grp;
    private long ans = 0;

    public long interactionCosts(int n, int[][] edges, int[] group) {
        grp = group;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        for (int c = 1; c <= 20; c++)
            solve(c, n);
        return ans;
    }

    private void solve(int c, int n) {
        boolean[] mark = new boolean[n];
        int tot = 0;
        for (int i = 0; i < n; i++) {
            if (grp[i] == c) {
                mark[i] = true;
                tot++;
            }
        }
        if (tot < 2)
            return;
        long[] cur = new long[1];
        dfs(0, -1, mark, tot, cur);
        ans += cur[0];
    }

    private int dfs(int u, int p, boolean[] mark, int tot, long[] cur) {
        int cnt = mark[u] ? 1 : 0;
        for (int v : g[u]) {
            if (v == p)
                continue;
            int sub = dfs(v, u, mark, tot, cur);
            cur[0] += (long) sub * (tot - sub);
            cnt += sub;
        }
        return cnt;
    }
}