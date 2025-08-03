class Solution {
    public int maxBalancedShipments(int[] arr) {
        int n = arr.length;
        int currMaxi = -1, count = 0;
        for (int i = 0; i < n; i++) {
            if (currMaxi == -1) currMaxi = arr[i];
            else {
                if (arr[i] < currMaxi) {
                    count++;
                    currMaxi = -1;
                }
                else currMaxi = Math.max(currMaxi, arr[i]);
            }
        }
        return count;
    }
}