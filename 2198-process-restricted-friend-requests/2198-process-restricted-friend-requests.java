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
        public DSU(DSU other) {
            this.parent = other.parent.clone();
            this.size = other.size.clone();
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
        public int Leader(int u) {
            if (u == parent[u])
                return u;
            return parent[u] = Leader(parent[u]);
        }
    }
    static class Pair {
        int u, v;
        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }
        @Override
        public String toString() {
            return "(" + u + " " + v + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.u == u && current.v == v;
        }
        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }
    }
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DSU dsu = new DSU(n + 1);
        DSU tempDSU = new DSU(n + 1);
        HashSet<Pair> set = new HashSet<>();
        for (int rest[] : restrictions) {
            set.add(new Pair(rest[0], rest[1])); 
        }

        boolean res[] = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0], v = requests[i][1];
            boolean flag = true;

            //They can never be friends
            if (set.contains(new Pair(u, v)) || set.contains(new Pair(v, u))) {
                res[i] = false;
                continue;
            }

            //if i make them friend do they violate the condition;
            if (dsu.Leader(u) == dsu.Leader(v)) {
                //they are friends;
                res[i] = true;
                continue;
            }
            tempDSU = new DSU(dsu);
            tempDSU.merge(u, v);
            for (Pair x : set) {
                int u1 = x.u, v1 = x.v;
                if (tempDSU.Leader(u1) == tempDSU.Leader(v1)) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                res[i] = true;
                dsu.merge(u, v); 
            }
            else {
                res[i] = false;
            }
        }
        return res;
    }
}