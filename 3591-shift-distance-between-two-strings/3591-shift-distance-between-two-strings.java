class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int n = s.length();
        long cost = 0;
        for (int i = 0; i < n; i++) {
            int current = s.charAt(i) - 'a';
            int req = t.charAt(i) - 'a';
            if (current == req) continue;
            long next = 0, prev = 0;
            for (int j = current; j != req; j = (j + 1) % 26) next += nextCost[j];
            for (int j = current; j != req; j = (j - 1 + 26) % 26) prev += previousCost[j];
            cost += Math.min(next, prev);
        }
        return cost;
    }
}