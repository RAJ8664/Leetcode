class Solution {
    public long minCost(int[] arr1, int[] arr2) {
        int n = arr1.length;
        HashMap<Integer, Integer> mp = new HashMap<>();
        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            mp.put(arr1[i], mp.getOrDefault(arr1[i], 0) + 1);
            minVal = Math.min(minVal, arr1[i]);
        }

        for (int i = 0; i < n; i++) {
            mp.put(arr2[i], mp.getOrDefault(arr2[i], 0) - 1);
            minVal = Math.min(minVal, arr2[i]);
        }

        List<Integer> diffList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            if (freq % 2 != 0) return -1;
            int count = Math.abs(freq) / 2;
            for (int i = 0; i < count; i++) {
                diffList.add(key);
            }
        }

        Collections.sort(diffList);
        int size = diffList.size() / 2;
        long cost = 0;

        for (int i = 0; i < size; i++) {
            cost += Math.min(2 * minVal, diffList.get(i));
        }

        return cost;
    }
}
