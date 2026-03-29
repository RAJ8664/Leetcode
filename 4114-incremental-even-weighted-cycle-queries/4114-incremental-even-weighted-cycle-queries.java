class Solution {

    static class DSU {
        int parent[], rank[], state[];
        public DSU(int n) {
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) parent[i] = i;
            rank = new int[n + 1];
            state = new int[n + 1];
        }

        public int[] find(int x) {
            if (parent[x] != x) {
                int res[] = find(parent[x]);
                state[x] ^= res[1];
                parent[x] = res[0];
            }
            return new int[]{parent[x], state[x]};
        }

        public void unite(int u, int v, int need) {
            if (rank[u] < rank[v]) {
                parent[u] = v;
                state[u] = need;
            } else if (rank[u] > rank[v]) {
                parent[v] = u;
                state[v] = need;
            } else {
                parent[v] = u;
                state[v] = need;
                rank[u]++;
            }
        }
    }
    
    public int numberOfEdgesAdded(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int total = 0;

        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int currU[] = dsu.find(u);
            int currV[] = dsu.find(v);

            int needU = currU[1], needV = currV[1];

            if (currU[0] != currV[0]) {
                dsu.unite(currU[0], currV[0], needU ^ needV ^ w);
                total++;
            } else {
                if ((needU ^ needV ^ w) == 0) total++;
            }
        }

        return total;
    }
}