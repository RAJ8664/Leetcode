import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    static class Tuple {
        int start, end, profit;
        public Tuple(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
        @Override
        public String toString() {
            return "Tuple{" +
                   "start=" + start +
                   ", end=" + end +
                   ", profit=" + profit +
                   '}';
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(first.start, second.start);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.end, second.end);
        }
    }
    private ArrayList<Tuple> arr;
    private int dp[];
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            arr.add(new Tuple(startTime[i], endTime[i], profit[i]));
        Collections.sort(arr, new customSort());
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(0);
    }
    private int solve(int ind) {
        if (ind >= arr.size())
            return 0;
        if (dp[ind] != -1)
            return dp[ind];

        int low = ind + 1, high = arr.size() - 1, next = arr.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid).start >= arr.get(ind).end) {
                next = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }

        int op1 = solve(ind + 1);
        int op2 = arr.get(ind).profit + solve(next);

        return dp[ind] = Math.max(op1, op2);
    }
}