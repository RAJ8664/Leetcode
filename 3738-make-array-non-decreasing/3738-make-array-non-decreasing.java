class Solution {
    public int maximumPossibleSize(int[] nums) {
        int n = nums.length;
        if (isSorted(nums)) return n;
        int count = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) set.add(nums[0]);
            else {
                int current = nums[i];
                if (set.higher(current) != null) count++;
                else {
                    set.add(nums[i]);
                }
            }
        }
        return n - count;
    }
    private boolean isSorted(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}