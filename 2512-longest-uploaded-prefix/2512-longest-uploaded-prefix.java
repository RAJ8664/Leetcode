class LUPrefix {
    private static long seg[];
    private static int arr[];
    private HashSet<Integer> set;
    public LUPrefix(int n) {
        seg = new long[4 * ((int)(1e5 + 1)) + 1];
        arr = new int[(int)(1e5 + 1)];
        Arrays.fill(arr, 0);
        Arrays.fill(seg, 0);
        set = new HashSet<>();
    }
    
    public void upload(int video) {
        if (set.contains(video)) return;
        set.add(video);
        update(arr, seg, 0, 0, (int)(1e5), video, video);
    }
    
    public int longest() {
        int low = 0;
        int high = (int)(1e5);
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    private boolean ok(int mid) {
        long current_sum = query(arr, seg, 0 , 0 , (int)(1e5) , 0 , mid);
        long req_sum = mid * 1L * (mid + 1) / 2;
        if (req_sum == current_sum) return true;
        return false;
    }

    //Segment Tree Implementation;
    private static void update(int arr[],long seg[],int ind, int low, int high, int index, int val){
        if(low == high){
            seg[ind] = val;
            return;
        }
        int mid = low + (high - low) / 2;
        if(index <= mid) update(arr,seg,2 * ind + 1, low, mid, index, val);
        else update(arr, seg,2 * ind + 2, mid + 1, high ,index, val);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];

    }
    private static long query(int arr[], long seg[], int ind, int low, int high , int l, int r){
        if(low >= l && high <= r) return seg[ind];
        if(low > r || high < l) return 0;
        int mid = low + (high - low) / 2;
        long left = query(arr, seg, 2 * ind + 1, low , mid, l ,r);
        long right = query(arr, seg,2 * ind + 2, mid + 1, high , l ,r);
        return left + right;
    }
    private static void build(int arr[], long seg[],int ind,int low, int high){
        if(low == high){
            seg[ind] = arr[low];
            return;
        }
        int mid = low + (high - low) / 2;
        build(arr, seg, 2 * ind + 1, low, mid);
        build(arr,seg,2 * ind + 2, mid + 1, high);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */