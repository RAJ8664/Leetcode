class Solution {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int len, int arr[], int k) {
        int n = arr.length;
        long currentSum = 0;
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
                currentSum += arr[i] * 1L;
            }
        }
        if (currentSum >= k)
            return true;
        int start = 0;
        for (int i = len; i < n; i++) {
            int current = arr[i], prev = arr[start];
            map.put(arr[start], map.getOrDefault(arr[start], 0) - 1);
            if (map.getOrDefault(arr[start], 0) == 0) {
                map.remove(arr[start]);
                set.remove(arr[start]);
                currentSum -= arr[start] * 1L;
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
                currentSum += arr[i] * 1L;
            }
            if (currentSum >= k)
                return true;
            start++;
        }
        return false;
    }
}