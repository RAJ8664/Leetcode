class Solution {
    static class Edge {
        int u, v, time;
        public Edge(int u, int v, int time) {
            this.u = u;
            this.v = v;
            this.time = time;
        }
        @Override
        public String toString() {
            return "(" + u + " " + v + " " + time + ")";
        }
    }
    static class DSU {
        int size[];
        int parent[];
        public DSU(int n) {
            size = new int[n + 1];
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }
        public void merge(int u, int v) {
            u = Leader(u);
            v = Leader(v);
            
            if (u == v) return;

            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent[v] = u;
            size[u] += size[v];
        }
        public int Leader(int u) {
            if (parent[u] == u) return u;
            return parent[u] = Leader(parent[u]);
        }
    }

    private ArrayList<Edge> edges;

    public int minTime(int n, int[][] connections, int k) {
        edges = new ArrayList<>();
        for (int edge[] : connections) {
            int u = edge[0], v = edge[1], time = edge[2];
            edges.add(new Edge(u, v, time));
        }
        int low = 0, high = (int)(1e9), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, k, n)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int target, int k, int n) {
        DSU dsu = new DSU(n);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).time <= target) 
                continue;
            dsu.merge(edges.get(i).u, edges.get(i).v);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.Leader(i) == i) 
                count++;
        }
        return count >= k;
    }
}