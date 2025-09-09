class Solution {
    private final int mod = (int)(1e9 + 7);
    private int freq[];
    static class Pair {
        int node, idx;
        public Pair(int node, int idx) {
            this.node = node;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "(" + node + " " + idx + ")";
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.node, first.node);
        }
    }
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        freq = new int[n];

        for (int query[] : requests) {
            int l = query[0], r = query[1];
            freq[l]++; if (r + 1 < n) freq[r + 1]--;
        }

        for (int i = 1; i < n; i++) 
            freq[i] += freq[i - 1];

        ArrayList<Pair> maxFreq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maxFreq.add(new Pair(freq[i], i));
        }
        Collections.sort(maxFreq, new customSort()); 

        ArrayList<Integer> values = new ArrayList<>();
        for (int ele : nums)
            values.add(ele);
        Collections.sort(values);
        int idx = values.size() - 1;

        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[maxFreq.get(i).idx] = values.get(idx--);

        int pref[] = new int[n];
        pref[0] = arr[0];
        for (int i = 1; i < n; i++) 
            pref[i] += pref[i - 1] + arr[i];

        long res = 0;
        for (int query[] : requests) {
            int l = query[0], r = query[1];
            long total = pref[r] * 1L;
            if (l - 1 >= 0) {
                total -= pref[l - 1] * 1L;
            }
            res = (res + total) % mod;
        }
        return (int)(res);    
    }
}