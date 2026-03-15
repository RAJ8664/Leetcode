class Solution {
    static class DSU {
        int parent[], size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int Leader(int u) {
            if (u == parent[u]) return u;
            return parent[u] = Leader(parent[u]); 
        }

        public void unite(int u, int v) {
            u = Leader(u);
            v = Leader(v);
            if (u == v) return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp; 
            }            
            size[u] += size[v];
            parent[v] = u;
        } 
    } 
    
    public int maxActivated(int[][] points) {
        int n = points.length;
        int id = 1;
        DSU dsu = new DSU(n + 1);
        HashMap<Integer, Integer>  row = new HashMap<>();
        HashMap<Integer, Integer> col = new HashMap<>();
        for (int[] point : points) {
            int r = point[0], c = point[1];
            if (row.containsKey(r))
                dsu.unite(row.get(r), id);
            if (col.containsKey(c))
                dsu.unite(col.get(c), id);
            row.putIfAbsent(r, id);
            col.putIfAbsent(c, id);
            id++;
        }        
        ArrayList<Integer> compSize = new ArrayList<>();
        for (int i = 1; i < id; i++) {
            if (dsu.Leader(i) == i) {
                compSize.add(dsu.size[i]);
            } 
        }
        Collections.sort(compSize);
        if (compSize.size() < 2) {
            return compSize.get(compSize.size() - 1) + 1; 
        }
        return compSize.get(compSize.size() - 1) +  compSize.get(compSize.size() - 2) + 1;
    }
}