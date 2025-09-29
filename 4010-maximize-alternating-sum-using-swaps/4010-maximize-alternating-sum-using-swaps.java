class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private HashMap<Integer, ArrayList<Integer>> components;
    private int vis[];
    private int cId;
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : swaps) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);        
        }

        components = new HashMap<>();
        vis = new int[n + 1];
        cId = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                ArrayList<Integer> current = new ArrayList<>();
                dfs(i, current);
                components.put(cId, current);
                cId++;
            }
        }

        long max = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> curr : components.entrySet()) {
            int key = curr.getKey();
            ArrayList<Integer> indices = curr.getValue();
            ArrayList<Integer> vals = new ArrayList<>();
            for (int ele : indices) 
                vals.add(nums[ele]);
            Collections.sort(vals);
            int l = 0, r = vals.size() - 1;
            for (int ele : indices) {
                if (ele % 2 == 0) {
                    max += vals.get(r--);
                }
                else 
                    max -= vals.get(l++);
            }
        }
        return max;
        
    }

    private void dfs(int u, ArrayList<Integer> current) {
        vis[u] = 1;
        current.add(u);
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                vis[v] = 1;
                dfs(v, current);
            }
        } 
    }
}