class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        ArrayList<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) 
                merged.add(nums1[i++]);
            else 
                merged.add(nums2[j++]);
        }
        while (i < n) 
            merged.add(nums1[i++]);
        while (j < m) 
            merged.add(nums2[j++]);
        if (merged.size() % 2 == 0) {
            int mid = merged.size() / 2;
            return ((merged.get(mid) * 1.0 + (merged.get(mid - 1)) * 1.0) / 2);
        } 
        return merged.get(merged.size() / 2);
    }
}