class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int i = 1; i <= 60; i++) {
            long current = (long) num1 - (long) i * num2; 
            if (current <= 0) 
                break; 
            int next = Long.bitCount(current); 
            if (next <= i && i <= current) 
                return i;
        }
        return -1;
    }
}
