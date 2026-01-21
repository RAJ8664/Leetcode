class Solution {
    public int[] minBitwiseArray(List<Integer> arr) {
        int n = arr.size();
        int nums[] = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) nums[i] = arr.get(i);
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < 32; j++) {
                if ((nums[i] & (1L << j)) == 0) {
                    long current = ((1L << (j - 1))); 
                    current ^= nums[i];  
                    if ((current | (current + 1)) == nums[i]) ans[k++] = ((int) current);
                    else ans[k++] = -1;
                    flag = true;
                    break;
                }
            }
            if (flag == false) ans[k++] = -1;
        }
        return ans;  
    }
}