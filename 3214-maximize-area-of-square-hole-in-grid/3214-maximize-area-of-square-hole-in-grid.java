class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars); Arrays.sort(vBars);
        return Math.min(solve(hBars), solve(vBars)) * Math.min(solve(hBars), solve(vBars));
    }
    
    private int solve(int arr[]) {
        int n = arr.length;
        int maxi = 1, curr = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] + 1 == arr[i]) {
                curr++;
                maxi = Math.max(maxi, curr);
            }
            else 
                curr = 1;
        }
        return maxi + 1;
    }
}