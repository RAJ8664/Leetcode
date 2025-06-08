class Solution {
    public boolean canMakeEqual(int[] nums, int k) {
        int n = nums.length;
        int copy[] = new int[n];
        for (int i = 0; i < n; i++) copy[i] = nums[i];
        int current_count = k;
        for (int i = 0; i < n - 1; i++) {
            if (copy[i] == -1) {
                if (current_count > 0) {
                    current_count--;
                    copy[i] = 1;
                    copy[i + 1] = -copy[i + 1];
                }
            }
        }
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (copy[i] == -1) flag = false;
        }
        if (flag == true) return true;
        for (int i = 0; i < n; i++) copy[i] = nums[i];
        current_count = k;
        for (int i = 0; i < n - 1; i++) {
            if (copy[i] == 1) {
                if (current_count > 0) {
                    current_count--;
                    copy[i] = -1;
                    copy[i + 1] = -copy[i + 1];
                }
            }
        }
        flag = true;
        for (int i = 0; i < n; i++) {
            if (copy[i] == 1) flag = false;
        }
        return flag;
    }
}