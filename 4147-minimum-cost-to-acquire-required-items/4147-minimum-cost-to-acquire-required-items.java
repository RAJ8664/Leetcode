class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        if (cost1 + cost2 >= costBoth) {
            long cost = Math.min(need1, need2) * 1L * costBoth;
            int mini = Math.min(need1, need2);
            need1 -= mini;
            need2 -= mini;
            if (need1 > 0) {
                if (cost1 >= costBoth)
                    cost += costBoth * 1L * need1;
                else
                    cost += need1 * 1L * cost1;
            }
            if (need2 > 0) {
                if (cost2 >= costBoth)
                    cost += costBoth * 1L * need2;
                else
                    cost += need2 * 1L * cost2;
            }
            return cost;
        } else
            return need1 * 1L * cost1 + need2 * 1L * cost2;
    }
}