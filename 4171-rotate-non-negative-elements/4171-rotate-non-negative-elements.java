class Solution {
    public int[] rotateElements(int[] nums, int k) {
        int n = nums.length;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) if (nums[i] >= 0) temp.add(nums[i]);
        if (temp.size() == 0) return nums;

        int arr[] = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) arr[i] = temp.get(i);

        k = k % arr.length;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) nums[i] = arr[idx++];
        }
        return nums;
    }
    
    private void reverse(int arr[], int low, int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--; 
        }
    }
}