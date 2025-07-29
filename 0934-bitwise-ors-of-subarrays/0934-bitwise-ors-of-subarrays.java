class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        HashSet<Integer> res = new HashSet<>();
        HashSet<Integer> prev = new HashSet<>();
        for (int i = 0; i < n; i++) {
            HashSet<Integer> current = new HashSet<>();
            current.add(arr[i]);
            for (int ele : prev) {
                current.add(arr[i] | ele);
            }
            prev = current;
            for (int ele : current) res.add(ele);
        }
        return res.size();
    }
}