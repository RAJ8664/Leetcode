class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long res = 0;
        boolean flag = false; ;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[n])
                flag = true;
            if (nums1[i] == nums2[i])
                continue;
            if (nums1[i] > nums2[i]) {
                if (nums2[n] <= nums1[i] && nums2[n] >= nums2[i]) 
                    flag = true;
            }
            if (nums1[i] < nums2[i]) {
                if (nums2[n] >= nums1[i] && nums2[n] <= nums2[i]) 
                    flag = true;
            }
            res += Math.abs(nums1[i] - nums2[i]);
        }
        if (flag == true)
            return res + 1;
        else {
            int mini = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                mini = Math.min(mini, Math.abs(nums2[i] - nums2[n]));
                mini = Math.min(mini, Math.abs(nums1[i] - nums2[n]));
            }
            res += mini + 1;
            return res;
        }
    }
}