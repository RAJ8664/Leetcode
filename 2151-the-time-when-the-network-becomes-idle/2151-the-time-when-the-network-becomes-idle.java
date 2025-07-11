import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static class Pair {
        int node, dist;
        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "node=" + node +
                   ", dist=" + dist +
                   '}';
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return Integer.compare(o1.dist, o2.dist);
        }
    }
    private ArrayList<ArrayList<Integer >> adj;
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int)(1e9));
        dist[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Pair(0, 0));
        while (pq.size() > 0) {
            int currNode = pq.peek().node, currDist = pq.peek().dist;
            pq.poll();
            for (int v : adj.get(currNode)) {
                if (dist[v] > currDist + 1) {
                    dist[v] = currDist + 1;
                    pq.offer(new Pair(v, currDist + 1));
                }
            }
        }
        int maxi = 0;
        for (int i = 1; i < n; i++) {
            int extra = (2 * dist[i] - 1) / patience[i];
            int timeReq = extra * patience[i] + 2 * dist[i];
            maxi = Math.max(maxi, timeReq);
        }
        return maxi + 1;
    }
}