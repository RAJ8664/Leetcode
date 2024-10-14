class Solution {
    static class custom_sort implements Comparator<Integer> {
        @Override
        public int compare(Integer first, Integer second) {
            return Integer.compare(second, first);
        }
    }
    public long maxKelements(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new custom_sort());
        for (int ele : nums) pq.offer(ele);
        while (k > 0) {
            k--;
            res += pq.peek();
            int current = pq.poll();
            if (current % 3 == 0) pq.offer(current / 3);
            else pq.offer((current / 3) + 1);
        }
        return res;
    }
}