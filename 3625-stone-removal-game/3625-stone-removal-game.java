class Solution {
    public boolean canAliceWin(int n) {
        if (n < 10) return false;
        int turn = 2, req = 9;
        n -= 10;
        while (n >= req) {
            n -= req;
            req--;
            if (turn == 2) turn = 1;
            else turn = 2;
        }
        return turn == 2;
    }
}