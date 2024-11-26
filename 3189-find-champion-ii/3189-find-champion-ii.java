class Solution {
    public int findChampion(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for(int current[] : edges) {
            int u = current[0];
            int v = current[1];
            adj.get(u).add(v);
        }
        int cnt = 0, ans = -1;
        for(int i = 0; i < n; i++) {
            int count[] = new int[1];
            int vis[] = new int[n + 1];
            dfs(i , adj, count, vis);
            if(count[0] == n) {
                cnt++;
                if(cnt > 1) return -1;
                ans = i;
            }
            count[0] = 0;
        }
        return ans;
    }
    private void dfs(int u , ArrayList<ArrayList<Integer>> adj , int count[], int vis[]) {
        count[0]++;
        vis[u] = 1;
        for(int v : adj.get(u)) {
            if(vis[v] == 0) dfs(v, adj, count,vis);
        }
    }
}