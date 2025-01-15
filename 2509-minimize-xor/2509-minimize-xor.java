class Solution {
    public int minimizeXor(int num1, int num2) {
        int numSetBits = count_setBits(num2);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((num1 & (1 << i)) != 0 && numSetBits > 0) {
                res |= (1 << i);
                numSetBits--;
            }
        }
        int index = 0;
        while (numSetBits > 0) {
            if ((res & (1 << index)) == 0) {
                res |= (1 << index);
                numSetBits--;
            }
            index++;
        }
        return res;
    }
    private int count_setBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}