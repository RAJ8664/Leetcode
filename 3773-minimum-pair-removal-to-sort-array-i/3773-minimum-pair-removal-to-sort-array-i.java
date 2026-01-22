class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int ele : nums) 
            temp.add(ele);
        int res = 0;
        while (!isSorted(temp)) {
            int idx = solve(temp);
            merge(temp, idx);
            res++;
        }
        return res;
    }

    private boolean isSorted(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1))
                return false;
        }
        return true;
    }

    private int solve(ArrayList<Integer> arr) {
        int n = arr.size();
        int mini = Integer.MAX_VALUE / 10, idx = -1;
        for (int i = 0; i < n - 1; i++) {
            int currSum = arr.get(i) + arr.get(i + 1);
            if (currSum < mini) {
                mini = currSum;
                idx = i;
            }
        }
        return idx;
    }

    private void merge(ArrayList<Integer> arr, int idx) {
        arr.set(idx, arr.get(idx) + arr.get(idx + 1));
        arr.remove(idx + 1);
    }
}