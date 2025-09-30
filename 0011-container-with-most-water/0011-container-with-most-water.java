class Solution {
    public int maxArea(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1, maxi = 0;
        while (left < right) {
            maxi = Math.max(maxi, Math.min(arr[left], arr[right]) * (right - left));
            if (arr[left] < arr[right])
                left++;
            else 
                right--;
        }
        return maxi;
    }
}