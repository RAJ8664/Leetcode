class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int count;
    private int vis[];
    private int indegree[];
    public int[] findRedundantDirectedConnection(int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= edges.length + 1; i++)
            adj.add(new ArrayList<>());
        for (int i = edges.length - 1; i >= 0; i--) {
            //i don't want to take the edge i, can we form a tree;
            for (int j = 0; j <= edges.length; j++)
                adj.get(j).clear();
            indegree = new int[edges.length + 1];
            for (int j = 0; j < edges.length; j++) {
                if (j != i) {
                    int u = edges[j][0], v = edges[j][1];
                    adj.get(u).add(v);
                    indegree[v]++;
                }
            }
            vis = new int[edges.length + 1];
            count = 0;
            int start = -1;
            for (int j = 1; j <= edges.length; j++) {
                if (indegree[j] == 0)  {
                    start = j;
                    break;
                }
            }
            if (start == -1) 
                continue;
            dfs(start, -1);
            if (count == edges.length) 
                return new int[]{edges[i][0], edges[i][1]}; 
        }
        return new int[]{-1, -1};
    }
    private void dfs(int u, int par) {
        count++;
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) 
                dfs(v, u);
        }
    }
}