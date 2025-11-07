class Solution {
    private long pref[];

    static class SegmentTree {
        private long[] tree;
        private int n;
        public SegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[2 * n];
            for (int i = 0; i < n; i++)
                tree[n + i] = arr[i];
            for (int i = n - 1; i > 0; i--)
                tree[i] = tree[2 * i] + tree[2 * i + 1];
        }

        public void update(int index, long val) {
            int pos = index + n;
            tree[pos] += val;
            while (pos > 1) {
                pos /= 2;
                tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
            }
        }

        public long query(int left, int right) {
            left += n;
            right += n;
            long sum = 0;
            while (left <= right) {
                if ((left & 1) == 1)
                    sum += tree[left++];
                if ((right & 1) == 0)
                    sum += tree[right--];
                left /= 2;
                right /= 2;
            }
            return sum;
        }
    }

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        pref = new long[n];
        pref[0] = stations[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + stations[i] * 1L;

        long low = 0, high = (long)(1e12), ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(mid, stations, r, k)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private boolean ok(long target, int arr[], int r, int k) {
        int n = arr.length;
        long val[] = new long[n];
        SegmentTree st = new SegmentTree(val);
        HashMap<Integer, Long> map = new HashMap<>();
        long extra = 0;
        for (int i = 0; i < n; i++) {
            int leftIdx = Math.max(0, i - r), rightIdx = Math.min(i + r, n - 1);
            long currSum = pref[rightIdx];
            if (leftIdx - 1 >= 0)
                currSum -= pref[leftIdx - 1];
            if (currSum >= target)
                continue;
            currSum += st.query(leftIdx, rightIdx);
            if (currSum >= target)
                continue;
            extra += target - currSum;
            st.update(rightIdx, target - currSum);
        }
        return extra <= k;
    }
}