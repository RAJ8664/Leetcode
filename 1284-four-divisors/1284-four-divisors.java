class Solution {
    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += solve(nums[i]);
        return sum;
    }

    private int solve(int n) {
        int count = 0, sum = 0;;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                sum += i;
                if (n / i != i) {
                    count++;
                    sum += n / i;
                }
            }
        }
        if (count == 4)
            return sum;
        return 0;
    }
}