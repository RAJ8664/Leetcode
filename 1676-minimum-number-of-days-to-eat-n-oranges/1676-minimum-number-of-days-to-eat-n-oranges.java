import java.util.HashMap;

class Solution {
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public int minDays(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (memo.containsKey(n))
            return memo.get(n);

        int op1 = (n % 2) + 1 + minDays(n / 2);
        int op2 = (n % 3) + 1 + minDays(n / 3);
        int result = Math.min(op1, op2);
        memo.put(n, result);

        return result;
    }
}