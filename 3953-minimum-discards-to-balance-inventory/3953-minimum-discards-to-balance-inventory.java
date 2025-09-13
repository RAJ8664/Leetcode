class Solution {
    public int minArrivalsToDiscard(int[] arr, int w, int m) {
        int n = arr.length;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> discarded = new HashSet<>();
        for (int i = 0; i < w; i++) {
            int current = arr[i];
            map.put(current, map.getOrDefault(current, 0) + 1);
            if (map.getOrDefault(current, 0) > m) {
                count++;
                discarded.add(i);
                map.put(current, map.getOrDefault(current, 0) -1);
            } 
        }
        int start = 0;
        for (int i = w; i < n; i++) {
            int current = arr[i];
            map.put(current, map.getOrDefault(current, 0) + 1);
            if (!discarded.contains(start))
                map.put(arr[start], map.getOrDefault(arr[start], 0) -1);

            if (map.getOrDefault(current, 0) > m) {
                map.put(current, map.getOrDefault(current, 0) -1);
                discarded.add(i);
                count++;
            }
            start++;
        }
        return count;
    }
}