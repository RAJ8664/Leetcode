class DataStream {
    static class Pair {
        int node, freq;
        public Pair(int node, int freq) {
            this.node = node;
            this.freq = freq;
        }
        @Override
        public String toString() {
            return "(" + node + " " + freq + ")";
        }
    }
    
    private Stack<Pair> st;
    private int need, time;
    public DataStream(int value, int k) {
        st = new Stack<>();
        this.need = value;
        this.time = k;
    }
    
    public boolean consec(int num) {
        if (st.size() == 0) {
            st.add(new Pair(num, 1));
        } else {
            if (st.peek().node == num) {
                st.add(new Pair(st.peek().node, st.peek().freq + 1));
            } else {
                st.add(new Pair(num, 1));
            }
        }
        if (st.peek().node == need && st.peek().freq >= time)
            return true;
        return false;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */