class Solution {
    public long sumAndMultiply(int n) {
        int temp = n;
        ArrayList<Integer> dig = new ArrayList<>();
        while (temp > 0) {
            if (temp % 10 != 0)
                dig.add(temp % 10);
            temp /= 10;
        }
        Collections.reverse(dig);
        long sum = 0;
        for (int ele : dig)
            sum += ele;
        int x = 0;
        for (int ele : dig)
            x = x * 10 + ele;
        return x * 1L * sum; 
    }
}