import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private HashMap<Integer, int[]> dp;
    private int vis[];
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        if (n == 0) return 1;
        adj = new ArrayList<>();
        vis = new int[n + 1];
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        if (checkCycle(n)) return -1;
        dp = new HashMap<>();
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (!dp.containsKey(i)) {
                int res[] = dfs(n, i, colors);
                for (int ele : res) maxi = Math.max(maxi, ele);
            }
        }
        return maxi;
    }
    private int[] dfs(int n, int u, String s) {
        if (dp.containsKey(u)) return dp.get(u);
        int ans[] = new int[26];
        vis[u] = 1;
        for (int v : adj.get(u)) {
            if (vis[v] == 0) {
                int child_ans[] = dfs(n, v, s);
                for (int i = 0; i < 26; i++) ans[i] = Math.max(ans[i], child_ans[i]);
            }
        }
        vis[u] = 0;
        ans[s.charAt(u) - 'a']++;
        dp.put(u, ans);
        return ans;
    }

    private boolean checkCycle(int n) {
        int indegree[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int v : adj.get(i)) indegree[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        int count = 0;
        while (q.size() > 0) {
            int curr_node = q.poll();
            count++;
            for (int v : adj.get(curr_node)) {
                indegree[v]--;
                if (indegree[v] == 0) q.add(v);
            }
        }
        if (count == n) return false;
        return true;
    }
}