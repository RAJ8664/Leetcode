class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int val[];
    private long res[];
    private int dp[][];
    private int subtree[];
    private MultiSet ms1[];
    private MultiSet ms2[];
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        adj = new ArrayList<>();
        for (int i = 0; i < cost.length + 2; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        val = new int[n];
        res = new long[n];
        subtree = new int[n];
        dp = new int[n][3];
        ms1 = new MultiSet[n];
        ms2 = new MultiSet[n];
        for (int i = 0; i < n; i++) {
            ms1[i] = new MultiSet();
            ms2[i] = new MultiSet();
        }

        for (int i = 0; i < cost.length; i++)
            val[i] = cost[i];
        
        dfs(0, -1);
        return res;
    }

    private void dfs(int u, int par) {
        if (adj.get(u).size() == 1 && u != 0) {
            subtree[u] = 1;
            res[u] = 1L;
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u);
            }
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                subtree[u] += subtree[v];
            }
        }
        subtree[u]++;
        if (subtree[u] < 3) {
            res[u] = 1;
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            for (int v : adj.get(u)) {
                if (v != par) {
                    ArrayList<Integer> m1 = ms1[v].getElements();
                    ArrayList<Integer> m2 = ms2[v].getElements();
                    for (int ele : m1) {
                        ms1[u].add(ele);
                        if (ms1[u].getSize() > 3) {
                            ms1[u].removeGreatest();
                        }
                    }
                    for (int ele : m2) {
                        ms2[u].add(ele);
                        if (ms2[u].getSize() > 3) {
                            ms2[u].removeSmallest();
                        }
                    }
                }
            }
        }
        else {
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            for (int v : adj.get(u)) {
                if (v != par) {
                    ArrayList<Integer> m1 = ms1[v].getElements();
                    ArrayList<Integer> m2 = ms2[v].getElements();
                    for (int ele : m1) {
                        ms1[u].add(ele);
                        if (ms1[u].getSize() > 3) {
                            ms1[u].removeGreatest();
                        }
                    }
                    for (int ele : m2) {
                        ms2[u].add(ele);
                        if (ms2[u].getSize() > 3) {
                            ms2[u].removeSmallest();
                        }
                    }
                }
            }
            long prod1 = 0, prod2 = 0;
            if (ms1[u].getSize() >= 2 && ms2[u].getSize() >= 1) {
                int first = ms1[u].getFirst(), second = ms1[u].getFirst(), third = ms2[u].getLast();
                prod1 = first * 1L * second * 1L * third;
                ms1[u].add(first); ms1[u].add(second); ms2[u].add(third);
            }

            if (ms2[u].getSize() >= 3) {
                int first = ms2[u].getLast(), second = ms2[u].getLast(), third = ms2[u].getLast();
                prod2 = first * 1L * second * 1L * third;
                ms2[u].add(first); ms2[u].add(second); ms2[u].add(third);
            }
            res[u] = Math.max(0, Math.max(prod1, prod2));
        }
    }

    static class MultiSet {
        TreeSet<Integer> set;
        HashMap<Integer, Integer> map;
        int size;
        public MultiSet() {
            set = new TreeSet<>();
            map = new HashMap<>();
            size = 0;
        }
        public void add(int val) {
            set.add(val);
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
        }
        public void remove(int val) {
            map.put(val, map.getOrDefault(val, 0) -1);
            if (map.getOrDefault(val, 0) == 0) set.remove(val);
            size--;    
        }
        public void removeGreatest() {
            int toRemove = set.last();
            map.put(toRemove, map.getOrDefault(toRemove, 0) -1);
            if (map.getOrDefault(toRemove, 0) == 0) set.remove(toRemove);
            size--;
        }
        public void removeSmallest() {
            int toRemove = set.first();
            map.put(toRemove, map.getOrDefault(toRemove, 0) -1);
            if (map.getOrDefault(toRemove, 0) == 0) set.remove(toRemove);
            size--;
        }
        public int getSize() {
            int res = 0;
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                res += curr.getValue();
            }
            return res;
        }
        public int getFirst() {
            int res = set.first();
            map.put(res, map.getOrDefault(res, 0) -1);
            if (map.getOrDefault(res, 0) == 0) set.remove(res);
            return res;
        }
        public int getLast() {
            int res = set.last();
            map.put(res, map.getOrDefault(res, 0) -1);
            if (map.getOrDefault(res, 0) == 0) set.remove(res);
            return res;
        }
        public ArrayList<Integer> getElements() {
            ArrayList<Integer> res = new ArrayList<>();
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                int key = curr.getKey();
                int val = curr.getValue();
                for (int j = 0; j < val; j++) res.add(key);
            }
            return res;
        }
        public String toString() {
            String res = "";
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                int key = curr.getKey();
                int val = curr.getValue();
                for (int j = 0; j < val; j++) res += ":" + key;
            }
            return res;
        }
    }
}