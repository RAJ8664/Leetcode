class Solution {
    private HashMap<Integer, ArrayList<Integer>> firstMap;
    private HashMap<Integer, ArrayList<Integer>> secondMap;

    public int minimumDifference(int[] nums) {
        int n = nums.length;
        firstMap = new HashMap<>();
        secondMap = new HashMap<>();

        int[] first = Arrays.copyOfRange(nums, 0, n / 2);
        int[] second = Arrays.copyOfRange(nums, n / 2, n);

        build(0, first, 0, 0, 1);
        build(0, second, 0, 0, 2);

        int total = Arrays.stream(nums).sum();
        int mini = Integer.MAX_VALUE;

        for (int i = 0; i <= n / 2; i++) {
            ArrayList<Integer> leftSums = firstMap.getOrDefault(i, new ArrayList<>());
            ArrayList<Integer> rightSums = secondMap.getOrDefault(n / 2 - i, new ArrayList<>());

            Collections.sort(rightSums);

            for (int ele1 : leftSums) {
                int target = total / 2 - ele1;
                int idx = Collections.binarySearch(rightSums, target);

                if (idx < 0) idx = -idx - 1;

                if (idx < rightSums.size()) {
                    int currentSum = ele1 + rightSums.get(idx);
                    int rem = total - currentSum;
                    mini = Math.min(mini, Math.abs(currentSum - rem));
                }
                if (idx > 0) {
                    int currentSum = ele1 + rightSums.get(idx - 1);
                    int rem = total - currentSum;
                    mini = Math.min(mini, Math.abs(currentSum - rem));
                }
            }
        }

        return mini;
    }

    private void build(int ind, int[] arr, int count, int sum, int id) {
        if (ind == arr.length) {
            if (id == 1)
                firstMap.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            else
                secondMap.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            return;
        }
        build(ind + 1, arr, count + 1, sum + arr[ind], id);
        build(ind + 1, arr, count, sum, id);                
    }
}
