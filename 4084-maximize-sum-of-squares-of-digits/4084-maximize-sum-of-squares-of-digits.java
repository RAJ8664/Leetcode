class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum)
            return "";
        StringBuilder res = new StringBuilder();
        int currSum = sum;
        for (int i = 0; i < num; i++) {
            for (int dig = 9; dig >= 0; dig--) {
                int remSum = currSum - dig;
                if (i == 0 && dig == 0 && num > 1)
                    continue;
                if (remSum >= 0 && remSum <= 9 * (num - i - 1)) {
                    res.append(dig);
                    currSum -= dig;
                    break;
                }
            }
        }
        if (res.length() != num || currSum != 0)
            return "";
        return res.toString();
    }
}