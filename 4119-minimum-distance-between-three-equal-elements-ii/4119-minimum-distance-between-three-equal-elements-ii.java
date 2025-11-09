class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (!map.containsKey(current))
                map.put(current, new ArrayList<>());
            map.get(current).add(i);
        }
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> res = new ArrayList<>();
            res = curr.getValue();
            if (res.size() < 3) continue;
            for (int i = 0; i < res.size() - 2; i++) {
                mini = Math.min(mini, Math.abs(res.get(i) - res.get(i + 1)) + Math.abs(res.get(i + 1) - res.get(i + 2))+ Math.abs(res.get(i + 2) - res.get(i)));
            }
        }
        return mini == Integer.MAX_VALUE ? -1 : mini;
    }
}