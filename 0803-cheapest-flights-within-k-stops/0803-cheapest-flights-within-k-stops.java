class Solution {
    private int dist[][];
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
    static class Tuple {
        int node, weight, used;
        public Tuple(int node, int weight, int used) {
            this.node = node;
            this.weight = weight;
            this.used = used;
        }
        @Override
        public String toString() {
            return "(" + node + " " + weight + " " + used + ")";
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.weight, second.weight);
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        dist = new int[n + 1][k + 2];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));
        dist[src][0] = 0;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : flights) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Tuple(src, 0, 0));
        while (pq.size() > 0) {
            int currNode = pq.peek().node, currCost = pq.peek().weight, currUsed = pq.peek().used;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childWeight = adj.get(currNode).get(i).weight;
                if (currUsed + 1 > k + 1) continue;
                if (dist[childNode][currUsed + 1] > dist[currNode][currUsed] + childWeight) {
                    dist[childNode][currUsed + 1] = dist[currNode][currUsed] + childWeight;
                    pq.offer(new Tuple(childNode, dist[childNode][currUsed + 1], currUsed + 1)); 
                } 
            }    
        }
        int mini = (int)(1e9);
        for (int i = 0; i <= k + 1; i++) {
            mini = Math.min(mini, dist[dst][i]); 
        }
        if (mini == (int)(1e9))
            return -1;
        return mini;
    }
}