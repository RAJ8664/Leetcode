class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();
        int left = 0, right = 0, extra = 0;
        for (int i = 0; i < n; i++) {
            char current = moves.charAt(i);
            if (current == 'L') left++;
            else if (current == 'R') right++;
            else extra++;
        }
        return Math.abs(left - right) + extra;
    }
}