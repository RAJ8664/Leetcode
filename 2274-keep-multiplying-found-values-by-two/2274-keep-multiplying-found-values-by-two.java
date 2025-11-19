class Solution {
    public int findFinalValue(int[] nums, int original) {
        int n = nums.length;
        int ans = original;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) 
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        while (true) {
            int x = ans;
            if (map.containsKey(x)) {
                ans = ans * 2;
                x = ans;
            } else 
                break; 
        }
        return ans;
    }
}