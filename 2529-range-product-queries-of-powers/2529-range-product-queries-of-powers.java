class Solution {
    private ArrayList<Integer> arr;
    private int mod = (int)(1e9 + 7);
    public int[] productQueries(int n, int[][] queries) {
        arr = new ArrayList<>();
        while (n > 0) 
            n -= change(n);
        Collections.sort(arr);
        long pre[] = new long[arr.size()];
        long prod = 1;
        for (int i = 0; i < arr.size(); i++) {
            prod = (prod * arr.get(i)) % mod;
            pre[i] = prod;
        }
        int res[] = new int[queries.length];
        int k = 0;
        for (int[] temp : queries) {
            int l = temp[0], r = temp[1];
            if (l == 0) {
                res[k++] = (int) pre[r];
            } else {
                long numerator = pre[r];
                long denominator = pre[l - 1];
                long inv = modPow(denominator, mod - 2, mod);
                res[k++] = (int) ((numerator * inv) % mod);
            }
        }
        return res;
    }
    private int change(int n) {
        int current = 1;
        while (current * 2 <= n) {
            current *= 2;
        }
        arr.add(current);
        return current;
    }
    private long modPow(long base, long exp, int m) {
        long result = 1;
        base %= m;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % m;
            base = (base * base) % m;
            exp >>= 1;
        }
        return result;
    }
}
