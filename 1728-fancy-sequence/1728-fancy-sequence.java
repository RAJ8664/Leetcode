class Fancy {
    private static final long MOD = 1_000_000_007L;
    private static final int MAXN = 100001;
    private long[] tree;     
    private long[] lazyMul;  
    private long[] lazyAdd;  
    private int size; 

    public Fancy() {
        tree    = new long[4 * MAXN];
        lazyMul = new long[4 * MAXN];
        lazyAdd = new long[4 * MAXN];
        size    = 0;
        Arrays.fill(lazyMul, 1L);
        Arrays.fill(lazyAdd, 0L);
    }

    private void pushDown(int node) {
        long mul = lazyMul[node];
        long add = lazyAdd[node];
        if (mul == 1 && add == 0) return; 
        for (int child : new int[]{2 * node, 2 * node + 1}) {
            lazyMul[child] = lazyMul[child] * mul % MOD;
            lazyAdd[child] = (lazyAdd[child] * mul + add) % MOD;
        }
        lazyMul[node] = 1L;
        lazyAdd[node] = 0L;
    }

    private void update(int node, int start, int end, int pos, long val) {
        if (start == end) {
            tree[node] = val % MOD;
            lazyMul[node] = 1L;
            lazyAdd[node] = 0L;
            return;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        if (pos <= mid) update(2 * node, start, mid, pos, val);
        else update(2 * node + 1, mid + 1, end, pos, val);
    }
    
    private void rangeUpdate(int node, int start, int end, int l, int r, long mul, long add) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            lazyMul[node] = lazyMul[node] * mul % MOD;
            lazyAdd[node] = (lazyAdd[node] * mul + add) % MOD;
            return;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        rangeUpdate(2 * node,     start, mid,   l, r, mul, add);
        rangeUpdate(2 * node + 1, mid + 1, end, l, r, mul, add);
    }

    private long query(int node, int start, int end, int pos) {
        if (start == end) {
            return (tree[node] * lazyMul[node] + lazyAdd[node]) % MOD;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        if (pos <= mid) return query(2 * node,     start, mid,   pos);
        else return query(2 * node + 1, mid + 1, end, pos);
    }

    public void append(int val) {
        size++;
        update(1, 1, MAXN, size, val);
    }

    public void addAll(int inc) {
        if (size == 0) return;
        rangeUpdate(1, 1, MAXN, 1, size, 1L, inc % MOD);
    }

    public void multAll(int m) {
        if (size == 0) return;
        rangeUpdate(1, 1, MAXN, 1, size, m % MOD, 0L);
    }

    public int getIndex(int idx) {
        if (idx >= size) return -1;
        return (int) query(1, 1, MAXN, idx + 1); 
    }
}
/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */