class Solution {
    public int finalValueAfterOperations(String[] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            String current = arr[i];
            if (current.charAt(0) == '+' || current.charAt(1) == '+')
                res++;
            else if (current.charAt(0) == '-' || current.charAt(1) == '-')
                res--;
        }
        return res;
    }
}