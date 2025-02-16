class Solution {
    public long maxWeight(int[] pizzas) {
        Arrays.sort(pizzas);
        long res = 0L;
        int l = 0, r = pizzas.length - 1;
        int count = pizzas.length / 8 + (pizzas.length / 4) % 2;
        for (int i = 0; i < count; i++) {
            res += (long) pizzas[r];
            r--;
            l += 3;
        }
        while (l < r) {
            res += (long) pizzas[r - 1];
            r -= 2;
            l += 2;
        }
        return res;
    }
}