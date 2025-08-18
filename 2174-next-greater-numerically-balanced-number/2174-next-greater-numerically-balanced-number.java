class Solution {
    public int nextBeautifulNumber(int n) {
        n++;
        while (true) {
            if (check(n) == true)
                return n;
            n++;
        }
    }
    private boolean check(int n) {
        int freq[] = new int[10];
        int temp = n;
        while (temp > 0) {
            freq[temp % 10]++;
            temp /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if (freq[i] == 0)
                continue;
            if (freq[i] != i)
                return false;
        }
        return true;
    }
}