class MovieRentingSystem {
    static class Pair {
        int movie, price;
        public Pair(int movie, int price) {
            this.movie = movie;
            this.price = price;
        }
        @Override
        public String toString() {
            return "(" + movie + " " + price + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.movie == movie && current.price == price;
        }
        @Override
        public int hashCode() {
            return Objects.hash(movie, price);
        }
    }

    static class Tuple {
        int shop, movie, price;
        public Tuple(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
        @Override
        public String toString() {
            return "(" + shop + " " + movie + " " + price + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Tuple current = (Tuple)(obj);
            return current.shop == shop && current.movie == movie && current.price == price;
        }
        @Override
        public int hashCode() {
            return Objects.hash(shop, movie, price);
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.price, second.price);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.movie, second.movie);
        }
    }

    static class customSortTuple implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(first.price, second.price);
            if (op1 != 0) return op1;
            int op2 = Integer.compare(first.shop, second.shop);
            if (op2 != 0) return op2;
            return Integer.compare(first.movie, second.movie);
        }
    }

    static class customSortShop implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.price, second.price);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.movie, second.movie); // movie field stores shopId here
        }
    }

    private HashMap<Integer, TreeSet<Pair>> map;
    private HashMap<Pair, Integer> shopMoviePrice; // key: Pair(shop, movie) stored as (movie=shop, price=movie)
    private TreeSet<Tuple> rentedMovies;
    private TreeSet<Tuple> unRentedMovies; 
    private HashMap<Integer, TreeSet<Pair>> unRentedMap;

    public MovieRentingSystem(int n, int[][] entries) {
        map = new HashMap<>();
        shopMoviePrice = new HashMap<>();
        rentedMovies = new TreeSet<>(new customSortTuple());
        unRentedMovies = new TreeSet<>(new customSortTuple());
        unRentedMap = new HashMap<>();

        for (int entry[] : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            if (!map.containsKey(shop))
                map.put(shop, new TreeSet<>(new customSort())); 
            map.get(shop).add(new Pair(movie, price));
            shopMoviePrice.put(new Pair(shop, movie), price);
            unRentedMovies.add(new Tuple(shop, movie, price));
            if (!unRentedMap.containsKey(movie)) 
                unRentedMap.put(movie, new TreeSet<>(new customSortShop()));
            unRentedMap.get(movie).add(new Pair(shop, price));
        }   
    }
    
    public List<Integer> search(int movie) {
        TreeSet<Pair> current = unRentedMap.get(movie);
        List<Integer> res = new ArrayList<>();
        if (current == null) return res;
        Iterator<Pair> it = current.iterator();
        while (res.size() < 5 && it.hasNext()) {
            res.add(it.next().movie); // here .movie holds shopId for unRentedMap
        } 
        return res; 
    }
    
    public void rent(int shop, int movie) {
        int price = shopMoviePrice.get(new Pair(shop, movie));
        Tuple t = new Tuple(shop, movie, price);
        rentedMovies.add(t);
        unRentedMovies.remove(t);
        TreeSet<Pair> set = unRentedMap.get(movie);
        if (set != null) set.remove(new Pair(shop, price));
    }
    
    public void drop(int shop, int movie) {
        int price = shopMoviePrice.get(new Pair(shop, movie));
        Tuple t = new Tuple(shop, movie, price);
        rentedMovies.remove(t); 
        unRentedMovies.add(t);
        if (!unRentedMap.containsKey(movie)) 
            unRentedMap.put(movie, new TreeSet<>(new customSortShop()));
        unRentedMap.get(movie).add(new Pair(shop, price));
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Tuple> it = rentedMovies.iterator();
        while (res.size() < 5 && it.hasNext()) {
            Tuple current = it.next();
            List<Integer> tempRes = new ArrayList<>();
            tempRes.add(current.shop); tempRes.add(current.movie);
            res.add(new ArrayList<>(tempRes));
        } 
        return res;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
