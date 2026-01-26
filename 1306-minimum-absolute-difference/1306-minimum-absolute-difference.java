class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int mini = Integer.MAX_VALUE / 10;
        for (int i = 0; i < n - 1; i++) 
            mini = Math.min(mini, Math.abs(arr[i] - arr[i + 1]));
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) == mini) {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i]); temp.add(arr[i + 1]);
                res.add(new ArrayList<>(temp));
            }
        }
        return res;
    }
}