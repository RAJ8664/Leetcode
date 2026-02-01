class Solution {
    public int countMonobit(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (isSame(i)) 
                count++;
        }
        return count;
    }

    private boolean isSame(int n) {
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(0))
                return false;
        }
        return true;
    }
}