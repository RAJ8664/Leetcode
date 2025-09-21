class Solution {
    public int minimumCardPickup(int[] cards) {
        int n = cards.length;
        int mini = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int current = cards[i];
            if (map.containsKey(current)) {
                mini = Math.min(mini, i - map.get(current) + 1);
                map.put(current, i);
            }
            else {
                map.put(current, i);
            }
        }
        return mini == Integer.MAX_VALUE ? -1 : mini; 
    }
}