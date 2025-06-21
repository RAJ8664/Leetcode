class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int pref[];
    private int val[];
    private HashMap<Integer, ArrayList<Integer>> map; /*key, val --> for each key, what are the k's asked */
    private HashMap<Pair, Integer> res; /* for each (key , val), what is my answer */
    private OrderStatisticSet[] node_set;
    
    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = par.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        map = new HashMap<>();
        node_set = new OrderStatisticSet[n + 1];
        res = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int u = i + 1, v = par[i];
            if (v != -1) {
                adj.get(u).add(v + 1);
                adj.get(v + 1).add(u);
            }
        }
        val = new int[n + 1];
        for (int i = 0; i < n; i++) val[i + 1] = vals[i]; 
        build_pref(n);
        for (int i = 0; i < queries.length; i++) {
            int key = queries[i][0] + 1, val = queries[i][1];
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(val);
        }
        dfs(1, -1);

        int answer[] = new int[queries.length];
        int idx = 0;
        for (int q[] : queries) {
            int u = q[0] + 1, k = q[1];
            answer[idx++] = res.get(new Pair(u, k));
        }
        return answer;
    }
    private void dfs(int u, int par) {
        if (adj.get(u).size() == 1 && u != 1) {
            OrderStatisticSet set = new OrderStatisticSet();
            set.add(pref[u]);
            node_set[u] = set;
            ArrayList<Integer> ks = new ArrayList<>();
            if (map.containsKey(u)) ks = map.get(u);
            for (int k : ks) {
                if (set.size() < k) res.put(new Pair(u, k), -1);
                else res.put(new Pair(u, k), find_kth_element(set, k));
            }
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u);
            }
        }
        OrderStatisticSet current_set = new OrderStatisticSet();
        current_set.add(pref[u]);
        for (int v : adj.get(u)) {
            if (v != par) {
                if (node_set[v].size() > current_set.size()) {
                    OrderStatisticSet temp = current_set;
                    current_set = node_set[v];
                    node_set[v] = temp;
                }
                for (int ele : node_set[v].sortedList) {
                    current_set.add(ele);
                }
            }
        }
        ArrayList<Integer> ks = new ArrayList<>();
        if (map.containsKey(u)) ks = map.get(u);
        for (int k : ks) {
            if (current_set.size() < k) res.put(new Pair(u, k), -1);
            else res.put(new Pair(u, k), find_kth_element(current_set, k));
        }
        node_set[u] = current_set;
    }
    private int find_kth_element(OrderStatisticSet oset, int k) {
        return oset.getKth(k - 1);
    }
    private void build_pref(int n) {
        pref = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        int vis[] = new int[n + 1];
        vis[1] = 1;
        pref[1] = val[1];
        while (q.size() > 0) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (vis[v] == 0) {
                    vis[v] = 1;
                    q.offer(v);
                    pref[v] = pref[u] ^ val[v];
                }
            }
        }
    }
    static class Pair {
        int key, val;
        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
            return "(" + key + " " + val + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.key == key && current.val == val;
        }
        @Override
        public int hashCode() {
            return Objects.hash(key, val);
        }
    }
    static class OrderStatisticSet {
        private TreeSet<Integer> set;
        private ArrayList<Integer> sortedList;
        public OrderStatisticSet() {
            set = new TreeSet<>();
            sortedList = new ArrayList<>();
        }
        public boolean add(int x) {
            if (set.add(x)) {
                int idx = Collections.binarySearch(sortedList, x);
                if (idx < 0) idx = -idx - 1;
                sortedList.add(idx, x);
                return true;
            }
            return false;
        }
        public Integer getKth(int k) {
            if (k < 0 || k >= sortedList.size()) return null;
            return sortedList.get(k);
        }
        public int size() {
            return set.size();
        }
    }
}