class Solution {
    private int freq[];
    public int getLeastFrequentDigit(int n) {
        freq = new int[10];
        int temp = n;
        while (temp > 0) {
            freq[temp % 10]++;
            temp /= 10;
        }
        int mini = Integer.MAX_VALUE, ans = -1;
        for (int i = 0; i <= 9; i++) {
            if (freq[i] == 0) continue;         
            if (freq[i] < mini) {
                mini = freq[i];
                ans = i; 
            }
        }
        return ans;
    }
}