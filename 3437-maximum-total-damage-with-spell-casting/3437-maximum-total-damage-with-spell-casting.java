class Solution {
    private long dp[];
    private HashMap<Integer, Integer> freq;
    public long maximumTotalDamage(int[] arr) {
        int n = arr.length;
        dp = new long[n + 1];
        Arrays.fill(dp, -1L);
        freq = new HashMap<>();
        for (int ele : arr) 
            freq.put(ele, freq.getOrDefault(ele, 0) + 1);
        ArrayList<Integer> nums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> curr : freq.entrySet()) 
            nums.add(curr.getKey());
        Collections.sort(nums);
        return solve(0, nums); 
    }
    private long solve(int ind, ArrayList<Integer> nums) {
        if (ind >= nums.size()) 
            return 0L;
        if (dp[ind] != -1) 
            return dp[ind] * 1L;
        long op1 = solve(ind + 1, nums);
        int nextIdx = bs(ind + 1, nums.get(ind), nums);
        long op2 = nums.get(ind)  * 1L *  freq.get(nums.get(ind)) + solve(nextIdx, nums);
        return dp[ind] = Math.max(op1, op2);
    }
    private int bs(int start, int target, ArrayList<Integer> arr) {
        int n = arr.size();
        int low = start, high = arr.size() - 1, ans = arr.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) > target + 2) {
                ans = mid;
                high = mid - 1;
            } else 
                low = mid + 1;
        } 
        return ans;
    }
}