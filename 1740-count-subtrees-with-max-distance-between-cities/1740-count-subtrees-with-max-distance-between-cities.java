class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int arr[];
    private ArrayList<ArrayList<Integer>> subsets;
    private int dp[][];
    private int depth[];

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i + 1;
        subsets = new ArrayList<>();

        getSubsets(0, arr, new ArrayList<>());

        dp = new int[n + 1][19];
        depth = new int[n + 1];
        dfs(1, 0);

        int res[] = new int[n - 1];
        for (ArrayList<Integer> curr : subsets) {
            int maxi = 0, count = 0;
            for (int i = 0; i < curr.size(); i++) {
                for (int j = i + 1; j < curr.size(); j++) {
                    int u = curr.get(i), v = curr.get(j);
                    if (adj.get(u).contains(v)) count++;
                    int dist = depth[u] + depth[v] - 2 * depth[lca(u, v)];
                    maxi = Math.max(maxi, dist);
                }
            }
            if (count == curr.size() - 1 && maxi > 0) res[maxi - 1]++;
        }
        return res;
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

    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u =  v;
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
    
    private int kthParent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1) 
                u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }

    private void getSubsets(int ind, int arr[], ArrayList<Integer> current) {
        if (ind >= arr.length) {
            if (current.size() <= 1) return;
            subsets.add(new ArrayList<>(current));
            return;
        }

        current.add(arr[ind]);
        getSubsets(ind + 1, arr, current);
        current.remove(current.size() - 1);
        getSubsets(ind + 1, arr, current);
    }
}