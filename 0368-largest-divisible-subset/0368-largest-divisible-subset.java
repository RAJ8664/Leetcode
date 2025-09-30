class Solution {
    private int count[];
    private int jump[];
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        count = new int[n];
        jump = new int[n];

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            count[i] = 1;
            jump[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (count[j] + 1 > count[i]) {
                        count[i] = count[j] + 1;
                        jump[i] = j;
                    } 
                }
            }
        }
        int maxi = Integer.MIN_VALUE, idx = -1;
        for (int i = 0; i < n; i++) {
            if (count[i] > maxi) {
                maxi = count[i];
                idx = i;
            }
        }
        while (idx != -1) {
            res.add(nums[idx]);
            idx = jump[idx];
        }
        return res;
    }
}