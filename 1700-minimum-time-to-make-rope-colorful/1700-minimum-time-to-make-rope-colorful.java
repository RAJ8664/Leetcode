class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int pref[] = new int[n];
        pref[0] = neededTime[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + neededTime[i];
        
        SparseMax sp = new SparseMax(neededTime);
        
        int totalTime = 0, i = 0;
        while (i < n) {
            if (i == n - 1) break;
            if (colors.charAt(i) != colors.charAt(i + 1)) {
                i++;
                continue;
            }
            
            int j = i;
            while (j + 1 < n && colors.charAt(j + 1) == colors.charAt(i)) {
                j++;
            }
            
            /* From i --> j we have same characters */
            totalTime += pref[j];
            if (i - 1 >= 0) 
                totalTime -= pref[i - 1];
            totalTime -= sp.query(i, j);
            
            i = j + 1;
        }
        return totalTime;
    }
    
    static class SparseMax {
        int[][] st;      
        int[] log;     
        int n;
        public SparseMax(int[] arr) {
            n = arr.length;
            log = new int[n + 1];
            buildLog();

            int k = log[n] + 1;
            st = new int[n][k];

            for (int i = 0; i < n; i++) {
                st[i][0] = arr[i];
            }

            for (int j = 1; j < k; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        private void buildLog() {
            log[1] = 0;
            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }
        }
        public int query(int L, int R) {
            int j = log[R - L + 1];
            return Math.max(st[L][j], st[R - (1 << j) + 1][j]);
        }
    }
}