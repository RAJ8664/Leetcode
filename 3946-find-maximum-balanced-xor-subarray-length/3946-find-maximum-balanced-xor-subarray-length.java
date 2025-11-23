import java.util.*;

class Solution {
    public int maxBalancedSubarray(int[] nums) {
        int n = nums.length;
        HashMap<String, Integer> map = new HashMap<>();
        int balance = 0, xor = 0, maxi = 0;
        map.put("0#0", -1);
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) 
                balance++;
            else 
                balance--;
            xor ^= nums[i];
            String key = balance + "#" + xor;
            if (map.containsKey(key)) {
                maxi = Math.max(maxi, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return maxi;
    }
}
