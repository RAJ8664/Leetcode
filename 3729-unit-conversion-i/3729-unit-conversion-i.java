class Solution {
    private ArrayList<ArrayList<Pair>> adj;
    private long mod = (long)(1e9 + 7);
    static class Pair {
        int node;
        long dist;
        public Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return "(" + node + " " + dist + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Long.compare(first.dist, second.dist);
        }
    }
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int current[] : conversions) {
            int u = current[0], v = current[1], wt = current[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        long dp[] = new long[n + 1];
        Arrays.fill(dp, (long)(2e19));
        dp[0] = 1;
        pq.offer(new Pair(0, 1));
        while (pq.size() > 0) {
            int curr_node = pq.peek().node;
            long curr_dist = pq.peek().dist;
            pq.poll();
            for (int i = 0; i < adj.get(curr_node).size(); i++) {
                int child_node = adj.get(curr_node).get(i).node;
                long child_dist = adj.get(curr_node).get(i).dist;
                if (dp[child_node] > (curr_dist * 1L * child_dist)) {
                    dp[child_node] = (int)((curr_dist * 1L * child_dist) % mod);
                    pq.offer(new Pair(child_node, dp[child_node]));
                }
            }
        }
        int res[] = new int[n + 1];
        for (int i = 0; i <= n; i++) res[i] = (int)(dp[i]);
        return res;
    }
}