class Solution {
    private ArrayList<HashSet<Integer>> adj;
    private HashMap<Integer, ArrayList<Integer>> map;
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        adj = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i <= (int)(1e5 + 1); i++) adj.add(new HashSet<>());
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums) set.add(ele);
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            compute_div(current);
        }
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> res = curr.getValue();
            for (int i = 0; i < res.size() - 1; i++) {
                int u = res.get(i);
                int v = res.get(i + 1);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
        }
        int vis[] = new int[(int)(1e5 + 1)];
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (vis[nums[i]] == 0) {
                ArrayList<Integer> res = new ArrayList<>();
                dfs(nums[i], vis, res);
                maxi = Math.max(maxi, res.size());
            }
        }
        return maxi;
    }

    private void dfs(int u , int vis[] , ArrayList<Integer> res) {
        res.add(u);
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) dfs(v, vis, res);
        }
    }

    private void compute_div(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (!map.containsKey(i)) map.put(i , new ArrayList<>());
                map.get(i).add(n);
                if (n / i != i) {
                    if (!map.containsKey(n / i)) map.put(n / i , new ArrayList<>());
                    map.get(n / i).add(n);
                }
            }
        }
        if (!map.containsKey(n)) map.put(n , new ArrayList<>());
        map.get(n).add(n);
    }
}