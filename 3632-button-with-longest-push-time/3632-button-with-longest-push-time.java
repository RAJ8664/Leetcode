class Solution {
    public int buttonWithLongestTime(int[][] events) {
        int n = events.length;
        int maxi_diff = 0, ans = n;
        for (int i = 0; i < n - 1; i++) {
            int current = events[i][1];
            int next = events[i + 1][1];
            if (next - current > maxi_diff) {
                maxi_diff = next - current;
                ans = events[i + 1][0];
                ans = Math.min(ans, events[i + 1][0]);
            }
            else if (next - current == maxi_diff) ans = Math.min(ans, events[i + 1][0]);
        }
        int current = 0;
        int next = events[0][1];
        if (next - current > maxi_diff) {
            maxi_diff = next - current;
            ans = events[0][0];
        } 
        else if (next - current == maxi_diff) ans = Math.min(ans, events[0][0]);
        return ans;
    }
}