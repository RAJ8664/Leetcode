class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(s.charAt(i) - '0');
        for (int k = 0; k < 1000; k++) {
            if (res.size() <= 1) return false;
            if (check(res)) return true;
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < res.size() - 1; i++) {
                temp.add((res.get(i) + res.get(i + 1)) % 10);
            }
            res.clear();
            for (int ele : temp) res.add(ele);
        }
        return false;
    }
    private boolean check(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int ele : arr) if (ele != arr.get(0)) return false;
        return true;
    }
}