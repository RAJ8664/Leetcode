class Solution {
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
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.weight, second.weight);
        }
    }
    private ArrayList<ArrayList<Pair>> adj;
    public int minCost(int n, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) 
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, 2 * wt));
        }
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));
        dist[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Pair(0, 0));
        while (pq.size() > 0) {
            int currNode = pq.peek().node, currWeight = pq.peek().weight;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childWeight = adj.get(currNode).get(i).weight;
                if (dist[childNode] > currWeight + childWeight) {
                    dist[childNode] = currWeight + childWeight;
                    pq.offer(new Pair(childNode, dist[childNode]));
                }
            }
        }
        if (dist[n - 1] == (int)(1e9)) 
            return - 1;
        return dist[n - 1];
    }
}