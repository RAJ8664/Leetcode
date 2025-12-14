class Solution {
    public long minMoves(int[] arr) {
        int n = arr.length;
        long currSum = 0;
        for (int ele : arr)
            currSum += ele * 1L;
        if (currSum < 0)
            return -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                idx = i;
                break;
            }
        }
        long ans = 0;
        if (idx == -1)
            return 0;
        long need = -arr[idx];
        for (int d = 1; d < n && need > 0; d++) {
            int left = (idx - d + n) % n, right = (idx + d) % n;;
            if (arr[left] > 0 && need > 0) {
                long take = Math.min(arr[left], need) * 1L;
                ans += take * d * 1L;
                need -= take;
                arr[left] -= take;
            }
            if (arr[right] > 0 && need > 0) {
                long take = Math.min(arr[right], need) * 1L;
                ans += take * d * 1L;
                need -= take;
                arr[right] -= take;
            }
        }
        return ans;
    }
}