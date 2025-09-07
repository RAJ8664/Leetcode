class Solution {
    public int[] sumZero(int n) {
        int res[] = new int[n];
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            res[i] = (i + 1) * -1;
            sum += (i + 1);
        } 
        res[n - 1] = sum;
        return res;
    }
}