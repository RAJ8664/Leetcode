class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : banned) set.add(ele);
        int current_sum = 0, count = 0;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i) && current_sum + i <= maxSum) {
                current_sum += i;
                count++;
            }
        }
        return count;
    }
}