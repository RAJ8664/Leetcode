class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int sum = 0;
        sum = nums[0];
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 1; i < n; i++) res.add(nums[i]);
        Collections.sort(res);
        sum += res.get(0);
        sum += res.get(1);
        return sum;
    }
}