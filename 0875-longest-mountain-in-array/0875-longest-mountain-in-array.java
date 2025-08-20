class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3)
            return 0;
        int count[] = new int[n];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
           if (arr[i] > arr[i - 1]) cnt++;
           else cnt = 1;
           count[i] += cnt; 
        }  
        cnt = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) cnt++;
            else cnt = 1;
            count[i] += cnt;
        }
        int maxi = 0;
        for (int i = 1; i < n - 1; i++) {
            if (count[i] > 2 && arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                maxi = Math.max(maxi, count[i] - 1);
            }
        } 
        return maxi;
    }
}