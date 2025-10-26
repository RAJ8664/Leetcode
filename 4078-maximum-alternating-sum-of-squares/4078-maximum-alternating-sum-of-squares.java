class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> temp = new ArrayList<>(); 
        for (int ele : nums)
            temp.add(Math.abs(ele));
        Collections.sort(temp);
        int arr[] = new int[n];
        int idx = 0, run = temp.size() - 1;
        while (idx < n) {
            arr[idx] = temp.get(run);
            run--;
            idx += 2;
        }
        idx = 1;
        while (idx < n) {
            arr[idx] = temp.get(run);
            run--;
            idx += 2;
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                res += arr[i] * 1L * arr[i];
            else
                res -= arr[i] * 1L * arr[i];
        }
        return res;
    }
}