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
            parent[v] = u;
            size[u] += size[v];
        }
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);
        for (int swap[]: allowedSwaps) 
            dsu.unite(swap[0], swap[1]);
        HashMap<Integer, ArrayList<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int leader = dsu.Leader(i);
            groups.computeIfAbsent(leader, k -> new ArrayList<>()).add(i);
        } 
        int res = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> curr: groups.entrySet()) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            ArrayList<Integer> idx = curr.getValue();
            for (int x : idx) {
                freq.put(source[x], freq.getOrDefault(source[x], 0) + 1); 
            }
            for (int x : idx) {
                if (freq.getOrDefault(target[x], 0) > 0) {
                    freq.put(target[x], freq.get(target[x]) - 1);
                } else {
                    res += 1;
                }
            }
        }
        return res;
    }
}