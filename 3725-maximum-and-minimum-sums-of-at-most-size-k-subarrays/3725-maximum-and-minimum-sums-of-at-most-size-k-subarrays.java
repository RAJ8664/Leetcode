class Solution {
    public long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] LMax = new long[n];
        long[] RMax = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]) stack.pop();
            if(stack.isEmpty()) LMax[i] = i + 1;
            else LMax[i] = i - stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.pop();
            if(stack.isEmpty()) RMax[i] = (long) (n - i);
            else RMax[i] = stack.peek() - i;
            stack.push(i);
        }
        long[] LMin = new long[n];
        long[] RMin = new long[n];
        stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) stack.pop();
            if(stack.isEmpty())  LMin[i] = i + 1;
            else  LMin[i] = i - stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i])  stack.pop();
            if(stack.isEmpty()) RMin[i] = (long) (n - i);
            else RMin[i] = stack.peek() - i;
            stack.push(i);
        }
        long ans = 0;
        for(int i = 0; i < n; i++) ans += nums[i] * solve(LMax[i], RMax[i], k) + nums[i] * solve(LMin[i], RMin[i], k);
        return ans;
    }
    public long solve(long L, long R, int k) {        
        if(L <= 0 || R <= 0) return 0;
        long total = 0;
        long X0 = (long)k - R;
        long leftCnt = 0;
        if(X0 >= 0) leftCnt = Math.min(L, X0 + 1);
        total += (long) R * leftCnt;
        long startX = leftCnt;
        long endX = L - 1;
        if(startX <= endX) {
            long realEnd = Math.min(endX, (long)k - 1);
            if(startX <= realEnd) {
                long count = realEnd - startX + 1;
                long a = startX;
                long b = realEnd;
                long sumX = (b * (b + 1) / 2) - ((a - 1) * a / 2);                 
                long temp = (long)k * count - sumX;
                total += temp;
            }
        }
        return Math.max(total, 0);
    }
}