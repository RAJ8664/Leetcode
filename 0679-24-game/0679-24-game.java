import java.util.*;
class Solution {
    private HashSet<State> subsets;
    private HashSet<stateExpr> expr;
    static class State {
        int a, b, c, d;
        public State(int a, int b, int c, int d) {
            this.a = a; this.b = b; this.c = c; this.d = d;
        }
        @Override
        public String toString() {
            return "(" + a + " " + b  + " " + c + " " + d + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State current = (State)(obj);
            return current.a == a && current.b == b && current.c == c && current.d == d;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }
    static class stateExpr {
        char a, b, c, d;
        public stateExpr(char a, char b, char c, char d) {
            this.a = a; this.b = b; this.c = c; this.d = d;
        }
        @Override
        public String toString() {
            return "(" + a + " " + b + " " + c + " " + d + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            stateExpr current = (stateExpr)(obj);
            return current.a == a && current.b == b && current.c == c && current.d == d;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }
    public boolean judgePoint24(int[] cards) {
        subsets = new HashSet<>();
        expr = new HashSet<>();
        getAllPermutation(cards);
        getAllStrings("+-/*");
        for (State currentState : subsets) {
            int[] nums = {currentState.a, currentState.b, currentState.c, currentState.d};
            if (canMake24(nums)) return true;
        }
        return false;
    }
    private boolean canMake24(double[] nums, int n) {
        if (n == 1) return Math.abs(nums[0] - 24) < 1e-6;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] next = new double[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++) 
                    if (k != i && k != j) next[idx++] = nums[k];
                for (double val : compute(nums[i], nums[j])) {
                    next[idx] = val;
                    if (canMake24(next, n - 1)) return true;
                }
            }
        }
        return false;
    }
    private boolean canMake24(int[] nums) {
        double[] arr = new double[nums.length];
        for (int i = 0; i < nums.length; i++) arr[i] = nums[i];
        return canMake24(arr, nums.length);
    }
    private List<Double> compute(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        if (Math.abs(b) > 1e-6) res.add(a / b);
        if (Math.abs(a) > 1e-6) res.add(b / a);
        return res;
    }
    private void getAllPermutation(int arr[]) {
        while (true) {
            State current = new State(arr[0], arr[1], arr[2], arr[3]);
            if (subsets.contains(current)) break;
            subsets.add(current);
            nextPermutation(arr);
        }
    }
    private void getAllStrings(String s) {
        while (true) {
            stateExpr current = new stateExpr(s.charAt(0), s.charAt(1), s.charAt(2), s.charAt(3));
            if (expr.contains(current)) break;
            expr.add(current);
            s = nextPermuteString(s);
        }
    }
    private void nextPermutation(int arr[]) {
        int n = arr.length;
        int idx = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                idx = i;
                break;
            }
        }
        if (idx == -1) reverse(arr, 0, n - 1);
        else {
            for (int i = n - 1; i > idx; i--) {
                if (arr[i] > arr[idx]) {
                    int temp = arr[i];
                    arr[i] = arr[idx];
                    arr[idx] = temp;
                    break;
                }
            }
            reverse(arr, idx + 1, n - 1);
        }
    }
    private String nextPermuteString(String s) {
        int n = s.length();
        int idx = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) < s.charAt(i + 1)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            StringBuilder res = new StringBuilder(s);
            return res.reverse().toString();
        } else {
            char s1[] = s.toCharArray();
            for (int i = n - 1; i > idx; i--) {
                if (s.charAt(i) > s.charAt(idx)) {
                    char temp = s1[i];
                    s1[i] = s1[idx];
                    s1[idx] = temp;
                    break;
                }
            }
            reverseString(s1, idx + 1, n - 1);
            StringBuilder ans = new StringBuilder();
            for (char c : s1) ans.append(c);
            return ans.toString();
        }
    }
    private void reverse(int arr[], int low, int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
    private void reverseString(char arr[], int low, int high) {
        while (low < high) {
            char temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
}