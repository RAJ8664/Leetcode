class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    private boolean ok(int target, int arr[]) {
        int n = arr.length;
        if (target > n) return false;
        HashMap<Long, Integer> freq = new HashMap<>();
        long mod = 1000000007L, base = 911382323L, hash = 0, power = 1;
        
        for (int i = 0; i < target - 1; i++) 
            power = (power * base) % mod;
        
        for (int i = 0; i < target; i++) 
            hash = (hash * base + arr[i] + 1007) % mod;
        
        freq.put(hash, 1);
        for (int i = target; i < n; i++) {
            hash = (hash - ((arr[i - target] + 1007) * power) % mod + mod) % mod;
            hash = (hash * base + arr[i] + 1007) % mod;
            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }
        
        for (int val : freq.values()) 
            if (val == 1) return true;
        
        return false;
    }
}