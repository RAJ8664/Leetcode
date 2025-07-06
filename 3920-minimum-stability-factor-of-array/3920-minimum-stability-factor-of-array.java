class Solution {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        if (maxC == 0)
            return find(nums);
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, maxC, nums)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        if (ans == -1)
            return 1;
        return ans - 1;
    }
    private boolean ok(int mid, int maxC, int arr[]) {
        int n = arr.length;
        int count = 0, i = 0;
        SparseGcd gcd = new SparseGcd(arr);
        while (i < n) {
            if (i + mid - 1 >= n)
                break;
            if (gcd.query(i, i + mid - 1) >= 2) {
                count++;
                i = i + mid;
            } else
                i++;
        }
        return count <= maxC;
    }
    private int find(int arr[]) {
        int n = arr.length;
        int count1 = 0;
        for (int ele : arr)
            if (ele == 1)
                count1++;
        if (count1 == n)
            return 0;
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, arr)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private boolean check(int mid, int arr[]) {
        SparseGcd gcd = new SparseGcd(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + mid - 1 < arr.length) {
                if (gcd.query(i, i + mid - 1) >= 2)
                    return true;
            }
        }
        return false;
    }
    static class SparseGcd {
        private int[][] sparseTable;
        private int n;

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public SparseGcd(int[] arr) {
            this.n = arr.length;
            int maxLog = (int)(Math.log(n) / Math.log(2)) + 1;
            this.sparseTable = new int[n][maxLog];

            for (int i = 0; i < n; i++)
                sparseTable[i][0] = arr[i];

            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 0; (i + (1 << j)) <= n; i++)
                    sparseTable[i][j] = gcd(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }

        public int query(int left, int right) {
            if (left < 0 || right >= n || left > right)
                throw new IllegalArgumentException("Invalid range");
            int j = (int)(Math.log(right - left + 1) / Math.log(2));
            return gcd(sparseTable[left][j], sparseTable[right - (1 << j) + 1][j]);
        }
    }


}