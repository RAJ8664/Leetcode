class Solution {
    public long maximumMedianSum(int[] nums) {
        int n = nums.length;
        long sum = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        Arrays.sort(nums);
        for (int ele : nums) dq.offerLast(ele);
        while (dq.size() > 0) {
            dq.pollFirst();
            dq.pollLast();
            sum += dq.pollLast();
        }
        return sum;
    }
}