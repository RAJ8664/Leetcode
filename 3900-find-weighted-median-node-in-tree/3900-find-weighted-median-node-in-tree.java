import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Pair {
        int node;
        long weight;
        public Pair(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + ")";
        }
    }
    private ArrayList<ArrayList<Pair >> adj;
    private long pref[];
    private int dp[][];
    private int depth[];
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            u++;
            v++;
            adj.get(u).add(new Pair(v, w * 1L));
            adj.get(v).add(new Pair(u, w * 1L));
        }
        Build_pref(n);
        dp = new int[n + 1][19];
        depth = new int[n + 1];
        dfs(1, 0);
        int res[] = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0] + 1, v = queries[i][1] + 1;
            if (u == v) {
                res[i] = u - 1;
                continue;
            }
            int lca = lca(u, v);
            long total_sum = pref[u] + pref[v] - 2 * pref[lca];

            /* u, . , . , lca, . . . , v */
            /* first check from u to lca if there exist some node */
            /* next check from lca to v */

            int check_left = check_left(u, lca, total_sum);
            if (check_left != -1)
                res[i] = check_left - 1;
            else
                res[i] = check_right(v, lca, total_sum, pref[u] - pref[lca]) - 1;
        }
        return res;
    }
    private int check_left(int u, int lca, long total) {
        int low = 0, high = depth[u] - depth[lca], ans = -1;
        double req = (double)(total * 1.0 / 2 * 1.0);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long sum = pref[u] - pref[find_kth_parent(u, mid)];
            if (sum >= req) {
                ans = find_kth_parent(u, mid);
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
    private int check_right(int v, int lca, long total, long prev_sum) {
        int low = 0, high = depth[v] - depth[lca], ans = -1;
        double req = (double)(total * 1.0 / 2 * 1.0);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long sum = prev_sum + pref[find_kth_parent(v, mid)] - pref[lca];
            if (sum >= req) {
                ans = find_kth_parent(v, mid);
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        v = find_kth_parent(v, diff);
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
    private int find_kth_parent(int u, int k) {
        int count = 0;
        while (k > 0) {
            if (k % 2 == 1)
                u = dp[u][count];
            count++;
            k >>= 1;
        }
        return u;
    }
    private void dfs(int u, int par) {
        dp[u][0] = par;
        for (int i = 1; i <= 18; i++)
            dp[u][i] = dp[dp[u][i - 1]][i - 1];
        for (int i = 0; i < adj.get(u).size(); i++) {
            int v = adj.get(u).get(i).node;
            if (v != par) {
                depth[v] = 1 + depth[u];
                dfs(v, u);
            }
        }
    }
    private void Build_pref(int n) {
        pref = new long[n + 1];
        int vis[] = new int[n + 1];
        pref[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        vis[1] = 1;
        while (q.size() > 0) {
            int curr_node = q.peek();
            q.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                long child_dist = adj.get(curr_node).get(i).weight;
                if (vis[child_node] == 0) {
                    vis[child_node] = 1;
                    pref[child_node] = pref[curr_node] + child_dist;
                    q.offer(child_node);
                }
            }
        }
    }
}