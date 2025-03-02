class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (check(ele, nums, k)) {
                maxi = Math.max(maxi, ele);
            }
        }
        if (maxi == Integer.MIN_VALUE) return -1;
        return maxi;
    }
    private boolean check(int target, int arr[], int k) {
        int n = arr.length;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        if (map.getOrDefault(target, 0) > 0) count++;
        int start = 0;
        for (int i = k; i < n; i++) {
            map.put(arr[start], map.getOrDefault(arr[start], 0) -1);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if (map.getOrDefault(target, 0) > 0) count++;
            start++;
        }
        return count == 1;
    }
}