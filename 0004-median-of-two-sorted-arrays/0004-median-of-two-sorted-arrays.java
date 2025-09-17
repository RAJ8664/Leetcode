class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int first = -1, second = -1;
        int i = 0, j = 0;
        int count = -1;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                count++;
                if (count == (n + m) / 2) {
                    first = nums1[i];
                }
                else if (count == (n + m) / 2 - 1) {
                    second = nums1[i];
                }
                i++;
            }
            else {
                count++;
                if (count == (n + m) / 2) {
                    first = nums2[j];
                }
                else if (count == (n + m) / 2 - 1) {
                    second = nums2[j];
                }
                j++;
            }
        }
        while (i < n) {
            count++;
            if (count == (n + m) / 2) {
                first = nums1[i];
            }
            else if (count == (n + m) / 2 - 1) {
                second = nums1[i];
            }
            i++;
        }
        while (j < m) {
            count++;
            if (count == (n + m) / 2) {
                first = nums2[j];
            }
            else if (count == (n + m) / 2 - 1) {
                second = nums2[j];
            }
            j++;
        }
        if ((n + m) % 2 == 0) {
            return ((first * 1.0 + second * 1.0) / 2);
        }
        return first;
    }
}