class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int ans[] = new int[2];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        int vis[] = new int[n + 1];
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            Arrays.fill(vis,0);
            if(cycle(u,-1,adj,vis) == true) {
                ans[0] = u;
                ans[1] = v;
                return ans;
            }
         }
         return ans;
    }
    public static boolean cycle(int u,int par,  ArrayList<ArrayList<Integer>> adj, int vis[]) {
        vis[u] = 1;
        boolean temp = false;
        for(int child : adj.get(u)) {
            if(vis[child] == 1 && child == par) continue;
            if(vis[child] == 1) return true;
            temp |= cycle(child, u, adj,vis);
        }
        return temp;
    }
}