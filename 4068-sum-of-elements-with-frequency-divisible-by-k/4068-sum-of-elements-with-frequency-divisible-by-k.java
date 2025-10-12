class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        int n = nums.length;
        int freq[] = new int[101];
        for (int ele : nums)
            freq[ele]++;
        int sum = 0;
        for (int i = 0; i <= 100; i++) 
            if (freq[i] % k == 0)
                sum += freq[i] * i;
        return sum;        
    }
}