class MovieRentingSystem {
    
    // Custom Pair class for shop-price mapping
    static class Pair {
        int movie, price;  // 'movie' is overloaded to store shopId
        
        public Pair(int movie, int price) {
            this.movie = movie;
            this.price = price;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair) obj;
            return current.movie == movie && current.price == price;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(movie, price);
        }
    }
    
    // Tuple class for complete movie information
    static class Tuple {
        int shop, movie, price;
        
        public Tuple(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Tuple current = (Tuple) obj;
            return current.shop == shop && 
                   current.movie == movie && 
                   current.price == price;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(shop, movie, price);
        }
    }
    
    // Comparator for sorting Tuples
    static class customSortTuple implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int priceCmp = Integer.compare(first.price, second.price);
            if (priceCmp != 0) return priceCmp;
            
            int shopCmp = Integer.compare(first.shop, second.shop);
            if (shopCmp != 0) return shopCmp;
            
            return Integer.compare(first.movie, second.movie);
        }
    }
    
    // Comparator for sorting shops by price
    static class customSortShop implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int priceCmp = Integer.compare(first.price, second.price);
            if (priceCmp != 0) return priceCmp;
            return Integer.compare(first.movie, second.movie); // movie stores shopId
        }
    }
    
    private HashMap<Pair, Integer> shopMoviePrice;
    private HashMap<Integer, TreeSet<Pair>> unRentedMap;
    private TreeSet<Tuple> rentedMovies;
    
    public MovieRentingSystem(int n, int[][] entries) {
        shopMoviePrice = new HashMap<>();
        rentedMovies = new TreeSet<>(new customSortTuple());
        unRentedMap = new HashMap<>();
        
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            shopMoviePrice.put(new Pair(shop, movie), price);
            
            if (!unRentedMap.containsKey(movie)) {
                unRentedMap.put(movie, new TreeSet<>(new customSortShop()));
            }
            unRentedMap.get(movie).add(new Pair(shop, price));
        }
    }
    
    public List<Integer> search(int movie) {
        TreeSet<Pair> current = unRentedMap.get(movie);
        List<Integer> res = new ArrayList<>();
        if (current == null) return res;
        
        Iterator<Pair> it = current.iterator();
        while (res.size() < 5 && it.hasNext()) {
            res.add(it.next().movie); // movie field holds shopId
        }
        return res;
    }
    
    public void rent(int shop, int movie) {
        int price = shopMoviePrice.get(new Pair(shop, movie));
        Tuple t = new Tuple(shop, movie, price);
        rentedMovies.add(t);
        
        TreeSet<Pair> set = unRentedMap.get(movie);
        if (set != null) {
            set.remove(new Pair(shop, price));
        }
    }
    
    public void drop(int shop, int movie) {
        int price = shopMoviePrice.get(new Pair(shop, movie));
        Tuple t = new Tuple(shop, movie, price);
        rentedMovies.remove(t);
        
        if (!unRentedMap.containsKey(movie)) {
            unRentedMap.put(movie, new TreeSet<>(new customSortShop()));
        }
        unRentedMap.get(movie).add(new Pair(shop, price));
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Tuple> it = rentedMovies.iterator();
        
        while (res.size() < 5 && it.hasNext()) {
            Tuple current = it.next();
            res.add(Arrays.asList(current.shop, current.movie));
        }
        return res;
    }
}