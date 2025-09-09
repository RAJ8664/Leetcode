class Solution {
    private int pref[][];
    static class customSort implements Comparator<Integer> {
        @Override
        public int compare(Integer first, Integer second) {
            return Integer.compare(first, second);
        }
    }
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;        
        
        buildPref(matrix);

        PriorityQueue<Integer> pq = new PriorityQueue<>(new customSort());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pq.offer(pref[i][j]);
                if (pq.size() > k) 
                    pq.poll(); 
            }
        }
        return pq.poll();
    }
    private void buildPref(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        pref = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0) 
                    pref[i][j] ^= pref[i - 1][j];
                if (j - 1 >= 0)
                    pref[i][j] ^= pref[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0) 
                    pref[i][j] ^= pref[i - 1][j - 1];
                pref[i][j] ^= arr[i][j];
            }
        }
    }
}