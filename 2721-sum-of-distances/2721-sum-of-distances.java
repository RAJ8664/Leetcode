class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int curr_ele = nums[i];
            if (map.containsKey(curr_ele)) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp = map.get(curr_ele);
                temp.add(i);
                map.put(curr_ele, temp);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(curr_ele, temp);
            }     
        }

        HashMap<Integer, ArrayList<Long>> pref = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            int key = curr.getKey();
            ArrayList<Integer> arr = new ArrayList<>();
            arr = curr.getValue();

            if (arr.size() == 0) continue;

            ArrayList<Long> prefix = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) 
                prefix.add(0L);
                
            prefix.set(0, arr.get(0) * 1L);
            for (int i = 1; i < arr.size(); i++) 
                prefix.set(i, prefix.get(i - 1) * 1L + arr.get(i) * 1L);
            pref.put(key, prefix);
        }

        long res[] = new long[n];
        for (int i = 0; i < n; i++) {
            int ele = nums[i];
            ArrayList<Integer> curr_idx = new ArrayList<>();
            ArrayList<Long> curr_pref = new ArrayList<>();
            curr_idx = map.get(ele);
            curr_pref = pref.get(ele);
            
            long total_sum = 0;
            int left_idx = binary_search_left(curr_idx, i);
            if (left_idx != -1) {
                total_sum = (total_sum + (left_idx + 1) * 1L * i);
                total_sum = (total_sum - curr_pref.get(left_idx));
            }
            total_sum = (total_sum + curr_pref.get(curr_pref.size() - 1));
            if (left_idx + 1 < curr_pref.size())
                total_sum -= curr_pref.get(left_idx + 1);
            total_sum -= i * 1L * (curr_pref.size() - (left_idx + 2));            

            res[i] = total_sum;
        }

        return res;
    }

    private int binary_search_left(ArrayList<Integer> arr, int target) {
        int n = arr.size();
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) < target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } 
        return ans;
    }
}