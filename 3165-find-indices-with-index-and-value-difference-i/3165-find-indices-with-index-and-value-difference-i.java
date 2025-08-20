class Solution {
    static class Pair {
        int node, idx;
        public Pair(int node, int idx) {
            this.node = node;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "(" + node + " " + idx + ")";
        }
    }
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        Pair maxSuff[] = new Pair[n];
        Pair minSuff[] = new Pair[n];
        maxSuff[n - 1] = new Pair(nums[n - 1], n - 1); minSuff[n - 1] = new Pair(nums[n - 1], n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > maxSuff[i + 1].node) {
                maxSuff[i] = new Pair(nums[i], i);
            }
            else 
                maxSuff[i] = new Pair(maxSuff[i + 1].node, maxSuff[i + 1].idx);
            if (nums[i] < minSuff[i + 1].node) {
                minSuff[i] = new Pair(nums[i], i);
            }
            else 
                minSuff[i] = new Pair(minSuff[i + 1].node, minSuff[i + 1].idx);
        } 
        for (int i = 0; i < n; i++) {
            int nextIdx = indexDifference + i;
            if (nextIdx < n) {
                int maxi = maxSuff[nextIdx].node;
                int mini = minSuff[nextIdx].node;
                if (Math.abs(nums[i] - maxi) >= valueDifference) {
                    return new int[]{i, maxSuff[nextIdx].idx};
                }
                if (Math.abs(nums[i] - mini) >= valueDifference) {
                    return new int[]{i, minSuff[nextIdx].idx};
                } 
            }
        }
        return new int[]{-1, -1};
    }
}