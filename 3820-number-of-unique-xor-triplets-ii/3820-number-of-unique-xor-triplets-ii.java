class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int ele[] = new int[4000];
        int set[] = new int[4000];
        for (int x : nums) ele[x]++;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) set[nums[i] ^ nums[j]]++;
        }
        int count = 0;
        for (int i = 0; i <= 2048; i++) {
            for (int j = 0; j <= 2048; j++) {
                if (set[j] > 0) {
                    int req = j ^ i;
                    if (req >= 4000) continue;
                    if (ele[req] > 0) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}