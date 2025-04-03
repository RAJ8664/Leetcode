class StockSpanner {
    private ArrayList<Integer> res;

    public StockSpanner() {
        res = new ArrayList<>();
    }
    
    public int next(int price) {
        res.add(price);
        int count = 0;
        int ind = res.size() - 1;
        while (ind >= 0) {
            if (res.get(ind) <= price) count++;
            else break;
            ind--;
        }
        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */