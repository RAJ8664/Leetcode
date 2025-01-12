class Solution {
    private ArrayList<ArrayList<Pair>> Revadj;
    static class Pair {
        int node, weight;
        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + ")";
        }
    }
    public int minMaxWeight(int n, int[][] edges, int threshold) {
        Revadj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) Revadj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            Revadj.get(v).add(new Pair(u, wt));
        }
        if (check(n) == false) return -1;
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));
        dist[0] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        while (q.size() > 0) {
            int u = q.peek().node;
            int curr_max = q.peek().weight;
            q.poll();
            for (int i = 0; i < Revadj.get(u).size(); i++) {
                int v = Revadj.get(u).get(i).node;
                int wt = Revadj.get(u).get(i).weight;
                if (dist[v] > Math.max(wt, curr_max)) {
                    dist[v] = Math.max(wt, curr_max);
                    q.offer(new Pair(v, dist[v]));
                }
            }
        }
        int maxi = dist[0];
        for (int i = 0; i < n; i++) maxi = Math.max(maxi, dist[i]);
        return maxi;
    }
    private boolean check(int n) {
        int vis[] = new int[n];
        ArrayList<Integer> nodes = new ArrayList<>();
        dfs(0, vis, nodes);
        return nodes.size() == n;
    }
    private void dfs(int u, int vis[], ArrayList<Integer> nodes) {
        nodes.add(u);
        vis[u] = 1;
        for (int i = 0; i < Revadj.get(u).size(); i++) {
            int v = Revadj.get(u).get(i).node;
            if (vis[v] == 0) dfs(v, vis, nodes);
        }
    }
}