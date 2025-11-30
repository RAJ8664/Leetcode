class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int rev = getReverse(nums[i]);
            if (map.containsKey(rev)) {
                res = Math.min(res, Math.abs(i - map.get(rev)));
            }
            map.put(nums[i], i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int getReverse(int n) {
        int temp = n;
        int res = 0;
        while (temp > 0) {
            res = res * 10 + temp % 10;
            temp /= 10;
        }
        return res;
    }
}