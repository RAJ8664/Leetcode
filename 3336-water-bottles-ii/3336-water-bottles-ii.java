class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res = numBottles;
        while (numBottles >= numExchange) {
            res++;
            numBottles -= numExchange;
            numBottles++;
            numExchange++;
        }
        return res;
    }
}