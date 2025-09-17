class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target >= matrix[mid][0] && target <= matrix[mid][m - 1]) {
                return BS(matrix[mid], target); 
            }
            else if (target > matrix[mid][m - 1]) {
                low = mid + 1;
            }
            else high = mid - 1;
        } 
        return false;
    }
    private boolean BS(int arr[], int target) {
        int n = arr.length;
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) 
                return true;
            else if (arr[mid] > target) {
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return false;
    }
}