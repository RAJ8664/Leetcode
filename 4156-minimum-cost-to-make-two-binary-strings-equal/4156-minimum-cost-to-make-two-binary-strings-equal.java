class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int n = s.length();
        long count0 = 0, count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) continue;
            if (s.charAt(i) == '1') count1++;
            else count0++;
        }
        long res1 = (count0 + count1) * 1L * flipCost;
        long res2 = Math.min(count0, count1) * 1L * swapCost + Math.abs(count0 - count1) * 1L * flipCost;
        long res3 = Math.min(count0, count1) * 1L * swapCost + (Math.abs(count0 - count1) / 2) * 1L *  (swapCost + crossCost) + (Math.abs(count0 - count1) % 2) * 1L * flipCost; 
        return Math.min(res1, Math.min(res2, res3));
    }
}