class Solution {
    static class Pair {
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class customSort implements Comparator<int[]> {
        @Override
        public int compare(int[] first, int[] second) {
            int op1 = Integer.compare(first[0], second[0]);
            if (op1 != 0)
                return op1;
            return Integer.compare(first[1], second[1]);
        }
    }
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, new customSort());
        int idx = 0;
        int i = 0, j = 0;
        ArrayList<Pair> temp = new ArrayList<>();
        while (i < n) {
            int start = intervals[i][0], end = intervals[i][1];
            while (j < n && intervals[j][0] <= end) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }
            temp.add(new Pair(start, end));
            i = j;
        }
        int res[][] = new int[temp.size()][2];
        for (int k = 0; k < temp.size(); k++) {
            res[k][0] = temp.get(k).start; 
            res[k][1] = temp.get(k).end;
        }
        return res;
    }
}