class Solution {
    private ArrayList<ArrayList<Pair>> adj;
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
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.weight , second.weight);
        }
    }
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        if (edges.length == 0) return 1;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int curr[] : edges) {
            int u = curr[0]; int v = curr[1]; int wt = curr[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        pq.offer(new Pair(0, 0));
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));
        dist[0] = 0;
        while (pq.size() > 0) {
            int curr_node = pq.peek().node;
            int curr_wt = pq.peek().weight;
            pq.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                int child_wt = adj.get(curr_node).get(i).weight;
                if (dist[child_node] > child_wt + dist[curr_node] + 1) {
                    dist[child_node] = child_wt + dist[curr_node] + 1;
                    pq.offer(new Pair(child_node, dist[child_node]));
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (dist[i] <= maxMoves) ans++;
        for (int curr[] : edges) {
            int u = curr[0]; int v = curr[1]; int wt = curr[2];
            int first = Math.max(0, maxMoves - dist[u]);
            int second = Math.max(0, maxMoves - dist[v]);
            ans += Math.min(wt, first + second);
        }
        return ans;
    }
}
