class Solution {
    
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return "(" + first + " " + second + ")";
        }
    }
   
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.first, second.first);
        }
    }

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Pair> res = new ArrayList<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            res.add(new Pair(num - k, 1)); res.add(new Pair(num + 1 + k , -1));
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Pair x : res) 
            set.add(x.first);
        for (int num : freq.keySet()) 
            set.add(num);
        Collections.sort(res, new custom_sort());
        int idx = 0, temp = 0, maxi = 0;
        for (int ele : set) {
            while (idx < res.size() && res.get(idx).first <= ele) {
                temp += res.get(idx).second;
                idx++;
            }
            int cnt = freq.getOrDefault(ele, 0);
            int curr = cnt + Math.min(numOperations, temp - cnt);
            maxi = Math.max(maxi, curr);
        }
        return maxi;
    }
}