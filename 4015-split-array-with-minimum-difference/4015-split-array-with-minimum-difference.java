class Solution {
    private boolean isInc[], isDec[];
    private long pref[], suff[];
    public long splitArray(int[] nums) {
        int n = nums.length;

        pref = new long[n];
        suff = new long[n];
        isInc = new boolean[n];
        isDec = new boolean[n];

        pref[0] = nums[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + nums[i];
        suff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) 
            suff[i] = suff[i + 1] + nums[i];
        isInc[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) 
                isInc[i] = isInc[i - 1] & true;
            else 
                isInc[i] = false;
        } 
        isDec[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) 
                isDec[i] = isDec[i + 1] & true;
            else 
                isDec[i] = false;
        }

        long mini = Long.MAX_VALUE / 10;;
        for (int i = 0; i < n - 1; i++) {
            if (isInc[i] == true && isDec[i + 1] == true) 
                mini = Math.min(mini, Math.abs(pref[i] - suff[i + 1]));
        }
        return mini == Long.MAX_VALUE / 10 ? -1 : mini; 
    }
}