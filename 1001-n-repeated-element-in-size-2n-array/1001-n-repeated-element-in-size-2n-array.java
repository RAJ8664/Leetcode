class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        int freq = n / 2;
        int count[] = new int[(int)(1e4 + 1)];
        for (int ele : nums) {
            count[ele]++;
            if (count[ele] == freq) return ele;
        }
        return -1;
    }
}