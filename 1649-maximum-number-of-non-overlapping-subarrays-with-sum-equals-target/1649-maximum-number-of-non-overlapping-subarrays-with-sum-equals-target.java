class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefSum = 0, count = 0;
        for (int i = 0; i < n; i++) {
            prefSum += nums[i];
            if (prefSum == target) {
                count++;
                prefSum = 0;
                map.clear();
            }
            else {
                int req = prefSum - target;
                if (map.containsKey(req)) {
                    count++;
                    prefSum = 0;
                    map.clear();
                }
                else {
                    if (!map.containsKey(prefSum)) 
                        map.put(prefSum, i);
                }
            } 
        }       
        return count; 
    }
}