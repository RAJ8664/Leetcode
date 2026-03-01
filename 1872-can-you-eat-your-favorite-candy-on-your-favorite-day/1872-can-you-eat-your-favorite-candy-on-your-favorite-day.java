class Solution {
    private long pref[]; 

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        pref = new long[n + 1];
        
        for (int i = 1; i <= n; i++) 
            pref[i] = pref[i - 1] + candiesCount[i - 1] * 1L;

        boolean res[] = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0], time = queries[i][1], limit = queries[i][2];
            long maxi = pref[type + 1] - 1, mini = pref[type] / limit;
            if (time <= maxi && time >= mini) 
                res[i] = true;
            else 
                res[i] = false;
        }  
        
        return res; 
    }
}