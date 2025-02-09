class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < elements.length; i++) {
            map.putIfAbsent(elements[i], new TreeSet<>());
            map.get(elements[i]).add(i);
        }   
        int res[] = new int[groups.length];
        for (int k = 0; k < groups.length; k++) {
            int current = groups[k];
            ArrayList<Integer> div = new ArrayList<>();
            int mini = Integer.MAX_VALUE;
            for (int i = 1; i * i <= current; i++) {
                if (current % i == 0) {
                    div.add(i);
                    if (current / i != i) div.add(current / i);
                }
            }
            for (int ele : div) {
                if (!map.containsKey(ele)) continue;
                mini = Math.min(mini, map.get(ele).first());
            }
            if (mini == Integer.MAX_VALUE) res[k] = -1;
            else res[k] = mini;
        }
        return res;
    }
}