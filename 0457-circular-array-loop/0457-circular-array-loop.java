class Solution {
    private ArrayList<ArrayList<Integer >> adj;
    private int parent[];
    private int vis[];
    private boolean res;

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        parent = new int[n + 1];
        Arrays.fill(parent, -1);
        vis = new int[n + 1];
        res = false;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) nums[i] = nums[i] % n;
            else {
                int current = -(nums[i]);
                nums[i] = (current % n);
                nums[i] = -nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                int next = (i + nums[i]) % n;
                if (next == i)
                    continue;
                adj.get(i).add(next);
            } else {
                int next = (i - Math.abs(nums[i]) + n) % n;
                if (i == next)
                    continue;
                adj.get(i).add(next);
            }
        }

        for (int i = 0; i < n; i++) {
            vis = new int[n + 1];
            dfs(i, -1, nums);
        }

        return res;
    }

    private void dfs(int u, int par, int arr[]) {
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                parent[v] = u;
                dfs(v, u, arr);
            } else if (vis[v] == 1) {
                if (check(u, v, arr) == true) {
                    res = true;
                    return;
                }
            }
        }
    }

    private boolean check(int u, int v, int arr[]) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[u]);
        int current = u;
        temp.add(arr[v]);
        while (current != v && current != -1) {
            temp.add(arr[current]);
            current = parent[current];
        }
        int pos = 0, neg = 0;
        if (temp.get(0) > 0)
            pos = 1;
        else
            neg = 1;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) > 0 && pos == 0)
                return false;
            if (temp.get(i) < 0 && neg == 0)
                return false;
        }
        return true;
    }
}