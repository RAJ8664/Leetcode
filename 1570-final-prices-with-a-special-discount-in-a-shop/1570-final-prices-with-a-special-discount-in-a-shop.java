class Solution {
    static class Pair {
        int node, ind;
        public Pair(int node, int ind) {
            this.node = node;
            this.ind = ind;
        }
        @Override
        public String toString() {
            return "(" + node + " " + ind + ")";
        }
    }
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int next_smallest[] = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = prices[i];
            while (st.size() > 0 && st.peek().node > current) st.pop();
            if (st.size() == 0) next_smallest[i] = 0;
            else next_smallest[i] = st.peek().node;
            st.add(new Pair(current, i));
        }
        int res[] = new int[n];
        for (int i = 0; i < n; i++) res[i] = prices[i] - next_smallest[i];
        return res;
    }
}