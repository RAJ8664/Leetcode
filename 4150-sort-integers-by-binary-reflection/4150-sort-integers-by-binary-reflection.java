class Solution {
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.second, second.second);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.first, second.first);
        }
    }
    public int[] sortByReflection(int[] nums) {
        int n = nums.length;
        ArrayList<Pair> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String bin = Integer.toBinaryString(nums[i]);
            String sb = new StringBuilder(bin).reverse().toString();
            int num = Integer.parseInt(sb, 2);
            res.add(new Pair(nums[i], num));
        }
        Collections.sort(res, new customSort());
        int ans[] = new int[n];
        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i).first;
        return ans;
    }
}