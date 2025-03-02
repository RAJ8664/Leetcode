class Solution {
    public int[] transformArray(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) res[i] = 0;
            else res[i] = 1;
        }
        Arrays.sort(res);
        return res;
    }
}