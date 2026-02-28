class Solution {
    private HashMap<String, Integer> map; 
    public int countSequences(int[] nums, long k) {
        int n = nums.length; 
        map = new HashMap<>();
        return solve(nums, k, 0, 0, 0, 0); 
    }

    private int solve(int arr[], long k, int idx, int p2, int p3, int p5) {
        if (idx >= arr.length) {
            if (p2 >= 0 && p3 >= 0 && p5 >= 0 && (k == Math.pow(2, p2) * 1L * Math.pow(3, p3) * 1L * Math.pow(5, p5))) {
                return 1;
            }
            return 0;
        }
        
        String key = idx + ":" + p2 + ":" + p3 + ":" + p5;
        if (map.containsKey(key)) 
            return map.get(key);

        int c2 = (arr[idx] == 4) ? 1 : 0, c3 = 0, c5 = (arr[idx] % 5 == 0) ? 1 : 0;
        if (arr[idx] % 2 == 0) c2++;
        if (arr[idx] % 3 == 0) c3++;
        
        int res = solve(arr, k, idx + 1, p2, p3, p5);
        res += solve(arr, k, idx + 1, p2 + c2, p3 + c3, p5 + c5);
        res += solve(arr, k, idx + 1, p2 - c2, p3 - c3, p5 - c5);

        map.put(key, res);
        
        return res;
    }
}