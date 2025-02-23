class Solution {
    private long dp[][];
    private HashMap<Integer, MultiSet<Integer>> map;
    public long maxSum(int[][] grid, int[] limits, int k) {
        int n = grid.length, m = grid[0].length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!map.containsKey(i)) map.put(i, new MultiSet<>());
                map.get(i).add(grid[i][j]);
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            MultiSet<Integer> current = map.get(i);
            ArrayList<Integer> r = new ArrayList<>();
            while (r.size() < limits[i]) {
                r.add(current.last());
                current.remove(current.last());
            }
            elements.add(new ArrayList<>(r));
        }
        for (ArrayList<Integer> x : elements) {
            for (int ele : x) arr.add(ele);
        }
        Collections.sort(arr);
        long ans = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (k == 0) break;
            ans += arr.get(i);
            k--;
        }
        return ans;
    }
    private long solve(int ind, int k, ArrayList<Integer> arr) {
        if (ind >= arr.size()) return 0;
        if (dp[ind][k] != -1) return dp[ind][k];
        long op1 = Integer.MIN_VALUE / 10, op2 = Integer.MIN_VALUE / 10;
        if (k > 0) op1 = arr.get(ind) + solve(ind + 1, k - 1, arr);
        op2 = solve(ind + 1, k, arr);
        return dp[ind][k] = Math.max(op1, op2);
    }
    static class MultiSet<T> {
        TreeMap<T, Integer> frequency;
        TreeSet<T> set;
        int size;
        public MultiSet() {
            set = new TreeSet<>();
            frequency = new TreeMap<>();
            size = 0;
        }
        public MultiSet(Comparator<T> cmp) {
            set = new TreeSet<>(cmp);
            frequency = new TreeMap<>(cmp);
            size = 0;
        }
        public void add(T elem) {
            if (frequency.get(elem) == null || frequency.get(elem) == 0) {
                frequency.put(elem, 0);
                set.add(elem);
            }
            frequency.put(elem, frequency.get(elem) + 1);
            size++;
        }
        public void remove(T elem) {
            if (!set.contains(elem)) return;
            frequency.put(elem, frequency.get(elem) - 1);
            if (frequency.get(elem) == 0) {
                set.remove(elem);
                frequency.remove(elem);
            }
            size--;
        }
        public boolean contains(T elem) {
            return set.contains(elem);
        }
        @Override
        public String toString() {
            String current = "(";
            for (T ele : set) {
                int freq = frequency.get(ele);
                for (int i = 0; i < freq; i++) {
                    if (current.length() == 1) current += ele;
                    else current += "," + ele;
                }
            }
            current += ")";
            return current;
        }
        public int count(T element) {return frequency.getOrDefault(element, 0);}
        public int size() {int size = 0; for(int count : frequency.values()) size += count; return size;}
        public T ceiling(T element) {return frequency.ceilingKey(element);}
        public T floor(T element) {return frequency.floorKey(element);}
        public T higher(T element) {return frequency.higherKey(element);}
        public T lower(T element) { return frequency.lowerKey(element);}
        public T last() {
            return set.last();
        }
    }
}