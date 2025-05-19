class Solution {
    static class Tuple {
        int ele, sum, idx;
        public Tuple(int ele, int sum, int idx) {
            this.ele = ele;
            this.sum = sum;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "(" + ele + " " + sum + " " + idx + ")";
        }
    }
    static class custom_sort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(first.sum, second.sum);
            if (op1 != 0) return op1;
            return Integer.compare(first.ele, second.ele);
        }
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new custom_sort());
        for (int i = 0; i < n; i++) {
            pq.offer(new Tuple(nums[i], compute_sum(nums[i]), i));
        }
        int count = 0, index = 0;
        int arr[] = new int[n];
        int loc[] = new int[n];
        while (pq.size() > 0) {
            int curr_ele = pq.peek().ele, curr_sum = pq.peek().sum , curr_idx = pq.peek().idx;
            arr[index++] = curr_idx;
            pq.poll();
        }
        int req[] = new int[n];
        for (int i = 0; i < n; i++) req[i] = arr[i];
        Arrays.sort(req);
        for (int i = 0; i < n; i++) loc[arr[i]] = i;
        for (int i = 0; i < n; i++) {
            if (arr[i] != req[i]) {
                count++;
                int idxx = loc[req[i]];
                loc[arr[i]] = idxx;
                int temp = arr[i];
                arr[i] = arr[idxx];
                arr[idxx] = temp;
            }
        }
        return count;
    }
    private int compute_sum(int n) {
        int res = 0;
        int temp = n;
        while (temp > 0) {
            res += temp % 10;
            temp /= 10;
        }
        return res;
    }
}