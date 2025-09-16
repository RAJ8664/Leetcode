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
            if (u == parent[u])
                return u;
            return parent[u] = Leader(parent[u]);
        }
        public void merge(int u, int v) {
            u = Leader(u);
            v = Leader(v);
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
    }
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DSU dsu = new DSU(n + 1);

        HashMap<Integer, Integer> rowId = new HashMap<>();
        HashMap<Integer, Integer> colId = new HashMap<>();
        HashSet<Integer> rowOccurred = new HashSet<>();
        HashSet<Integer> colOccurred = new HashSet<>();
        
        int currId = 1;
        for (int i = 0; i < stones.length; i++) {
            int x = stones[i][0], y = stones[i][1];
            
            if (rowOccurred.contains(x) && colOccurred.contains(y)) {
                //This stones made two components to join;
                int getRowId = rowId.get(x);
                int getColId = colId.get(y);

                dsu.merge(getRowId, getColId);
                dsu.merge(currId, getRowId);

                rowOccurred.add(x);
                colOccurred.add(y);                
            }

            else if (rowOccurred.contains(x)) {
                // It matches only with one of the rows;
                int getRowId = rowId.get(x);

                dsu.merge(getRowId, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

                colId.put(y, currId);
            }
            else if (colOccurred.contains(y)) {
                // It matches with only one of the col;
                int getColId = colId.get(y);

                dsu.merge(getColId, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

                rowId.put(x, currId);
            }
            else {
                // It does not matches with any of the row's or col's

                colId.put(y, currId);
                rowId.put(x, currId);

                rowOccurred.add(x);
                colOccurred.add(y);

            }
            currId++;
        }
        int count = 0;
        for (int i = 1; i < currId; i++) {
            if (dsu.Leader(i) == i) {
                count += dsu.size[i] - 1;
            }
        }
        return count;
    }
}