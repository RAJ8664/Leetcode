class Solution {
    public int smallestNumber(int n) {
        while (true) {
            int new_num = n + 1;
            int temp = new_num & (new_num - 1);
            if (temp == 0) return n;
            n++;
        }   
    }
}