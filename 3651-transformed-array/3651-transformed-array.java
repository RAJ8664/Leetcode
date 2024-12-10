class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ele = nums[i];
            if (ele >= 0) {
                if (i + ele < n) res.add(nums[i + ele]);
                else res.add(nums[(i + ele) % n]);
            }
            else {
                if (i - Math.abs(ele) >= 0) res.add(nums[i - Math.abs(ele)]);
                else res.add(nums[(i - Math.abs(ele) % n + n) % n]);
            }
        }
        int ans[] = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}