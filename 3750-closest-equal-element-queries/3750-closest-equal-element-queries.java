class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
        }
        for (int ele : queries) {
            int val = nums[ele];
            TreeSet<Integer> current = new TreeSet<>();
            current = map.get(val);
            int dist = Integer.MAX_VALUE / 10;
            if (current.higher(ele) != null) {
                dist = Math.min(dist, Math.abs(ele - current.higher(ele)));
                int new_dist = n - 1 - current.last() + (ele + 1);
                dist = Math.min(dist, new_dist);
            }
            if (current.lower(ele) != null) {
                int last_ind = current.first();
                int new_dist = n - (ele + 1) + (last_ind + 1);
                dist = Math.min(dist, new_dist);
                dist = Math.min(dist, Math.abs(ele - current.lower(ele)));
            }
            if (dist == Integer.MAX_VALUE / 10) res.add(-1);
            else res.add(dist);
        }
        return res;
    }
}