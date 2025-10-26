class Solution {
    public long countStableSubarrays(int[] arr) {
        long n = arr.length, res = 0, pre = 0;
        Map<Long, Map<Long, Long>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey((long)arr[i])) {
                Map<Long, Long> t = map.get((long)arr[i]);
                Long cnt = t.get(pre - arr[i]);
                if (cnt != null) 
                    res += cnt;
            }
            pre += arr[i];
            Map<Long, Long> curr = map.computeIfAbsent((long)arr[i], k -> new HashMap<>());
            curr.put(pre, curr.getOrDefault(pre, 0L) + 1L);
            if (i > 0 && arr[i] == 0 && arr[i - 1] == 0) 
                res--;
        }
        return res;
    }
}