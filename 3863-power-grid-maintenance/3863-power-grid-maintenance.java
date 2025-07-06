class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int currentId;
    private HashMap<Integer, Integer> idMap;
    private HashMap<Integer, TreeSet<Integer>> connectedComponents;
    private int vis[];

    public int[] processQueries(int n, int[][] connections, int[][] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) 
            adj.add(new ArrayList<>());
        for (int edge[] : connections) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        currentId = 1;
        idMap = new HashMap<>();
        connectedComponents = new HashMap<>();
        vis = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                dfs(i, -1);
                currentId++;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0], node = queries[i][1];
            if (type == 2) {
                int id = idMap.get(node);
                if (connectedComponents.get(id).contains(node)) 
                    connectedComponents.get(id).remove(node);
            }
            else {
                int id = idMap.get(node);
                int ansNode = -1;
                if (connectedComponents.get(id).contains(node)) {
                    res.add(node);
                    continue;
                }
                else if (connectedComponents.get(id).size() > 0) 
                    ansNode = connectedComponents.get(id).first();
                res.add(ansNode); 
            }
        }
        int ans[] = new int[res.size()];
        for (int i = 0; i < res.size(); i++) 
            ans[i] = res.get(i);
        return ans;
    }
    private void dfs(int u, int par) {
        vis[u] = 1;
        
        idMap.put(u, currentId);
        if (!connectedComponents.containsKey(currentId)) 
            connectedComponents.put(currentId, new TreeSet<>());   
        connectedComponents.get(currentId).add(u);
        
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                dfs(v, u);
            }
        }
    }
}