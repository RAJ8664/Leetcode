class Solution {
    static class DSU {
        int parent[], size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void unite(int u, int v) {
            u = Leader(u); v = Leader(v);
            if (u == v)
                return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            size[u] += size[v];
            parent[v] = u;
        }
        public int Leader(int u) {
            if (u == parent[u]) 
                return u;
            return parent[u] = Leader(parent[u]);
        }
    }

    public int numberOfComponents(int[][] properties, int k) {
        DSU dsu = new DSU(properties.length + 1);
        for (int i = 0; i < properties.length; i++) {
            HashSet<Integer> curr = new HashSet<>();
            for (int ele : properties[i]) 
                curr.add(ele);
            for (int j = i + 1; j < properties.length; j++) {
                HashSet<Integer> newH = new HashSet<>();
                for (int ele : properties[j]) 
                    newH.add(ele);
                int count = 0;
                for (int ele : curr) 
                    if (newH.contains(ele)) 
                        count++;
                if (count >= k) 
                    dsu.unite(i, j); 
            }
        }        

        int res = 0;
        for (int i = 0; i < properties.length; i++) {
            if (dsu.Leader(i) == i) 
                res++;
        } 
        return res;
    }
}