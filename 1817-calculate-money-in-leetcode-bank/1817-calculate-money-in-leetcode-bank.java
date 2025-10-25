class Solution {
    public int totalMoney(int n) {
        int total = 0, current = 1, start = 1, count = 1;
        while (n > 0) {
            if (count > 7) {
                start++;
                current = start;
                count = 1;
            }
            total += current;
            count++;
            current++;
            n--;
            
        }
        return total;
    }
}