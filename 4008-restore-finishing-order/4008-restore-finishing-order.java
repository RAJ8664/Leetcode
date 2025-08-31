class Solution {
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
            return Integer.compare(first.idx, second.idx);
        }
    }
    public int[] recoverOrder(int[] order, int[] friends) {
        int n = order.length;
        int pos[] = new int[n + 1];
        for (int i = 0; i < n; i++) 
            pos[order[i]] = i;
        ArrayList<Pair> temp = new ArrayList<>();
        for (int i = 0; i < friends.length; i++)
            temp.add(new Pair(friends[i], pos[friends[i]]));
        Collections.sort(temp, new customSort()); 
        int res[] = new int[friends.length];
        for (int i = 0; i < temp.size(); i++)
            res[i] = temp.get(i).node;
        return res;
    }
}