class Solution {
    public int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++)
            res += get(i);
        return res;
    }
    private int get(int n) {
        ArrayList<Integer> dig = new ArrayList<>();
        while (n > 0) {
            dig.add(n % 10);
            n /= 10;
        }
        Collections.reverse(dig);
        System.out.println(dig);
        int count = 0;
        for (int i = 1; i < dig.size() - 1; i++) {
            if (dig.get(i) > dig.get(i + 1) && dig.get(i) > dig.get(i - 1))
                count++;
            else if (dig.get(i) < dig.get(i + 1) && dig.get(i) < dig.get(i - 1))
                count++;
        }
        return count;
    }
}