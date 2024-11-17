class Solution {
    public int minDifference(int[] arr) {
        int n = arr.length;
        List<Integer> nums = new ArrayList<>();
        int maxi = Integer.MIN_VALUE, mini = Integer.MAX_VALUE;
        int count = 0;
        for (int ele : arr) {
            maxi = Math.max(maxi, ele);
            if (ele != -1) mini = Math.min(mini, ele);
            if (ele == -1) count++;
            nums.add(ele);
        }
        if (count == n) return 0;
        if (count == 0) {
            maxi = 0;
            for (int i = 0; i < n - 1; i++) maxi = Math.max(maxi, Math.abs(arr[i] - arr[i + 1]));
            return maxi;
        }
        int low = 0, high = (int)(1000000005), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, new ArrayList<>(nums))) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int mid, ArrayList<Integer> nums) {
        int n = nums.size();
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != -1 && 
                ((i > 0 && nums.get(i - 1) == -1) || (i < nums.size() - 1 && nums.get(i + 1) == -1))) {
                mini = Math.min(mini, nums.get(i) - mid);
                maxi = Math.max(maxi, nums.get(i) + mid);
            }
        }
        if (maxi == Integer.MIN_VALUE || mini == Integer.MAX_VALUE) return true;
        mini += 2 * mid;
        maxi -= 2 * mid;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == -1) {
                boolean current = 
                    (i == 0 || Math.abs(nums.get(i - 1) - mini) <= mid) &&
                    (i == nums.size() - 1 || nums.get(i + 1) == -1 || Math.abs(nums.get(i + 1) - mini) <= mid);
                if (current == true) nums.set(i, mini);
                else nums.set(i, maxi);
            }
        }
        for (int i = 0; i < nums.size() - 1; i++) {
            if (Math.abs(nums.get(i) - nums.get(i + 1)) > mid) return false;
        }
        return true;
    }
}