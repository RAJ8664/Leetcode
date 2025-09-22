class Solution {
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        int freq[] = new int[101];
        int maxi = 0, count = 0;
        for (int ele : nums) {
            freq[ele]++;
            if (freq[ele] > maxi) {
                maxi = freq[ele];
                count = 1;
            }
            else if (freq[ele] == maxi) {
                count++;
            }
        }
        return count * maxi;
    }
}