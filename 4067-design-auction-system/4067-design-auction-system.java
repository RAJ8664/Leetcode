
class AuctionSystem {
    private HashMap<Pair, Integer> bids;
    private HashMap<Integer, TreeSet<ItemPair >> items;

    static class Pair {
        int userId, itemId;
        public Pair(int userId, int itemId) {
            this.userId = userId;
            this.itemId = itemId;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return p.userId == userId && p.itemId == itemId;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(userId, itemId);
        }
    }

    static class ItemPair {
        int userId, bidAmount;
        public ItemPair(int userId, int bidAmount) {
            this.userId = userId;
            this.bidAmount = bidAmount;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof ItemPair) {
                ItemPair p = (ItemPair) o;
                return p.userId == userId && p.bidAmount == bidAmount;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(userId, bidAmount);
        }
    }

    public AuctionSystem() {
        bids = new HashMap<>();
        items = new HashMap<>();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        Pair key = new Pair(userId, itemId);
        if (bids.containsKey(key))
            removeBid(userId, itemId);
        bids.put(key, bidAmount);
        items.putIfAbsent(itemId, new TreeSet<>(
        (a, b) -> {
            if (a.bidAmount != b.bidAmount)
                return b.bidAmount - a.bidAmount;
            return b.userId - a.userId;
        }
                          ));
        items.get(itemId).add(new ItemPair(userId, bidAmount));
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        Pair key = new Pair(userId, itemId);
        int oldAmount = bids.get(key);
        TreeSet<ItemPair> set = items.get(itemId);
        set.remove(new ItemPair(userId, oldAmount));
        bids.put(key, newAmount);
        set.add(new ItemPair(userId, newAmount));
    }

    public void removeBid(int userId, int itemId) {
        Pair key = new Pair(userId, itemId);
        int oldAmount = bids.get(key);
        bids.remove(key);
        TreeSet<ItemPair> set = items.get(itemId);
        set.remove(new ItemPair(userId, oldAmount));
        if (set.isEmpty())
            items.remove(itemId);
    }

    public int getHighestBidder(int itemId) {
        if (!items.containsKey(itemId) || items.get(itemId).isEmpty())
            return -1;
        return items.get(itemId).first().userId;
    }
}