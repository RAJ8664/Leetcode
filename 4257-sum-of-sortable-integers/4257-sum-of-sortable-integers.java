class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> factors = new ArrayList<>();
        factors = getFactors(n);
        int res = 0;
        for (int ele : factors) {
            if (check(ele, nums)) res += ele;
        }
        return res;
    }

    private boolean check(int k, int arr[]) {
        int n = arr.length;
        int ranges[][] = new int[n / k][2];
        int idx = 0, curr = 0;
        while (curr < n) {
            ranges[idx][0] = curr;
            ranges[idx][1] = curr + k - 1;
            idx++;
            curr = curr + k;
        }
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < ranges.length; i++) {
            int currLeft = ranges[i][0], currRight = ranges[i][1];
            int currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE;
            int drop = 0;
            for (int j = 0; j < k; j++) {
                int val = arr[currLeft + j];
                currMin = Math.min(currMin, val);
                currMax = Math.max(currMax, val);
                int nextVal = arr[currLeft + ((j + 1) % k)];
                if (val > nextVal) 
                    drop++;
            }
            if (drop > 1) return false;
            if (i > 0) {
                if (currMin < prevMax) return false;
            }
            prevMax = currMax;
        }
        return true;
    }

    private ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                res.add(i);
                if (num / i != i) {
                    res.add(num / i);
                }
            }
        }
        return res;
    }
}