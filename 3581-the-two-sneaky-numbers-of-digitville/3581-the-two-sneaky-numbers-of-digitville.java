class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        int freq[] = new int[n];
        for (int i = 0; i < n; i++) 
            freq[nums[i]]++;
        int res[] = new int[2];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (freq[i] > 1) 
                res[k++] = i;
        }
        return res;
    }
}