class Solution {
    private int nextGreater[];
    private int prevGreater[];
    static class Pair {
        int node, idx;
        public Pair(int node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;

        buildNextGreater(nums);
        buildPrevGreater(nums);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nextGreater[i] != -1 && prevGreater[i] != -1) 
                ans++;
        } 
        return ans;
    }

    private void buildNextGreater(int arr[]) {
        int n = arr.length;
        nextGreater = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().node <= current) {
                st.pop();
            }
            if (st.size() == 0)
                nextGreater[i] = -1;
            else 
                nextGreater[i] = st.peek().idx;
            st.add(new Pair(current, i));
        }
    }
    
    private void buildPrevGreater(int arr[]) {
        int n = arr.length;
        prevGreater = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().node <= current) 
                st.pop();
            if (st.size() == 0)
                prevGreater[i] = -1;
            else 
                prevGreater[i] = st.peek().idx;
            st.add(new Pair(current, i));
        }
    }
}