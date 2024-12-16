class Solution {
    private HashMap<Long, Long> memo;
    public int integerReplacement(int n) {
        memo = new HashMap<>();
        long res = solve((long)(n));
        return (int)(res);
    }
    private long solve(long n) {
        if (n == 1) return 0;
        if (memo.containsKey((long)(n))) return memo.get(n);
        if (n % 2 == 0) return 1 + solve(n / 2);
        long op1 = 1 + solve(n + 1);
        long op2 = 1 + solve(n - 1);
        memo.put(n , (long)(Math.min(op1, op2)));
        return (long)(Math.min(op1, op2));
    }
}
