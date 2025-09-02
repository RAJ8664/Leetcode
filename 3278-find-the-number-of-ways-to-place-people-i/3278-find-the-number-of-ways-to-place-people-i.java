class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (valid(i, j, points)) 
                    count++; 
            }
        }    
        return count;
    }
    private boolean valid(int s, int e, int arr[][]) {
        int n = arr.length;
        if (arr[s][0] > arr[e][0] || arr[s][1] < arr[e][1]) 
            return false;
        for (int i = 0; i < n; i++) {
            if (i == s || i == e) 
                continue;
            if (arr[i][0] >= arr[s][0] && arr[i][0] <= arr[e][0] && arr[i][1] <= arr[s][1] && arr[i][1] >= arr[e][1]) {
                return false;
            } 
        }
        return true;
    }
}