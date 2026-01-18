class Solution {
    static class Pair {
        int cost, capacity;
        public Pair(int cost, int capacity) {
            this.cost = cost;
            this.capacity = capacity;
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            return Integer.compare(a.cost, b.cost);
        }
    }

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        int maxi = 0;
        ArrayList<Pair> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new Pair(costs[i], capacity[i]));
            if (costs[i] < budget)
                maxi = Math.max(maxi, capacity[i]);
        }
        Collections.sort(res, new customSort());

        int[] prefMaxi = new int[n];
        prefMaxi[0] = res.get(0).capacity;
        for (int i = 1; i < n; i++)
            prefMaxi[i] = Math.max(prefMaxi[i - 1], res.get(i).capacity);
        for (int i = 0; i < n; i++) {
            int remaining = budget - res.get(i).cost;
            if (remaining <= 0)
                continue;
            int idx = bs(res, remaining - 1, i - 1);
            if (idx != -1)
                maxi = Math.max(maxi, res.get(i).capacity + prefMaxi[idx]);
        }
        return maxi;
    }

    private int bs(ArrayList<Pair> arr, int target, int hi) {
        int low = 0, high = hi, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid).cost <= target) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
}
