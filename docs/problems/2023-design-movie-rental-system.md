# 2023. Design Movie Rental System

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/design-movie-rental-system/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2023-design-movie-rental-system){ .md-button }

---

<h2><a href="https://leetcode.com/problems/design-movie-rental-system">2023. Design Movie Rental System</a></h2><h3>Hard</h3><hr><p>You have a movie renting company consisting of <code>n</code> shops. You want to implement a renting system that supports searching for, booking, and returning movies. The system should also support generating a report of the currently rented movies.</p>

<p>Each movie is given as a 2D integer array <code>entries</code> where <code>entries[i] = [shop<sub>i</sub>, movie<sub>i</sub>, price<sub>i</sub>]</code> indicates that there is a copy of movie <code>movie<sub>i</sub></code> at shop <code>shop<sub>i</sub></code> with a rental price of <code>price<sub>i</sub></code>. Each shop carries <strong>at most one</strong> copy of a movie <code>movie<sub>i</sub></code>.</p>

<p>The system should support the following functions:</p>

<ul>
	<li><strong>Search</strong>: Finds the <strong>cheapest 5 shops</strong> that have an <strong>unrented copy</strong> of a given movie. The shops should be sorted by <strong>price</strong> in ascending order, and in case of a tie, the one with the <strong>smaller </strong><code>shop<sub>i</sub></code> should appear first. If there are less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.</li>
	<li><strong>Rent</strong>: Rents an <strong>unrented copy</strong> of a given movie from a given shop.</li>
	<li><strong>Drop</strong>: Drops off a <strong>previously rented copy</strong> of a given movie at a given shop.</li>
	<li><strong>Report</strong>: Returns the <strong>cheapest 5 rented movies</strong> (possibly of the same movie ID) as a 2D list <code>res</code> where <code>res[j] = [shop<sub>j</sub>, movie<sub>j</sub>]</code> describes that the <code>j<sup>th</sup></code> cheapest rented movie <code>movie<sub>j</sub></code> was rented from the shop <code>shop<sub>j</sub></code>. The movies in <code>res</code> should be sorted by <strong>price </strong>in ascending order, and in case of a tie, the one with the <strong>smaller </strong><code>shop<sub>j</sub></code> should appear first, and if there is still tie, the one with the <strong>smaller </strong><code>movie<sub>j</sub></code> should appear first. If there are fewer than 5 rented movies, then all of them should be returned. If no movies are currently being rented, then an empty list should be returned.</li>
</ul>

<p>Implement the <code>MovieRentingSystem</code> class:</p>

<ul>
	<li><code>MovieRentingSystem(int n, int[][] entries)</code> Initializes the <code>MovieRentingSystem</code> object with <code>n</code> shops and the movies in <code>entries</code>.</li>
	<li><code>List&lt;Integer&gt; search(int movie)</code> Returns a list of shops that have an <strong>unrented copy</strong> of the given <code>movie</code> as described above.</li>
	<li><code>void rent(int shop, int movie)</code> Rents the given <code>movie</code> from the given <code>shop</code>.</li>
	<li><code>void drop(int shop, int movie)</code> Drops off a previously rented <code>movie</code> at the given <code>shop</code>.</li>
	<li><code>List&lt;List&lt;Integer&gt;&gt; report()</code> Returns a list of cheapest <strong>rented</strong> movies as described above.</li>
</ul>

<p><strong>Note:</strong> The test cases will be generated such that <code>rent</code> will only be called if the shop has an <strong>unrented</strong> copy of the movie, and <code>drop</code> will only be called if the shop had <strong>previously rented</strong> out the movie.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MovieRentingSystem&quot;, &quot;search&quot;, &quot;rent&quot;, &quot;rent&quot;, &quot;report&quot;, &quot;drop&quot;, &quot;search&quot;]
[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
<strong>Output</strong>
[null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]

<strong>Explanation</strong>
MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
movieRentingSystem.search(1);  // return [1, 0, 2], Movies of ID 1 are unrented at shops 1, 0, and 2. Shop 1 is cheapest; shop 0 and 2 are the same price, so order by shop number.
movieRentingSystem.rent(0, 1); // Rent movie 1 from shop 0. Unrented movies at shop 0 are now [2,3].
movieRentingSystem.rent(1, 2); // Rent movie 2 from shop 1. Unrented movies at shop 1 are now [1].
movieRentingSystem.report();   // return [[0, 1], [1, 2]]. Movie 1 from shop 0 is cheapest, followed by movie 2 from shop 1.
movieRentingSystem.drop(1, 2); // Drop off movie 2 at shop 1. Unrented movies at shop 1 are now [1,2].
movieRentingSystem.search(2);  // return [0, 1]. Movies of ID 2 are unrented at shops 0 and 1. Shop 0 is cheapest, followed by shop 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= entries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= shop<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= movie<sub>i</sub>, price<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>Each shop carries <strong>at most one</strong> copy of a movie <code>movie<sub>i</sub></code>.</li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>search</code>, <code>rent</code>, <code>drop</code> and <code>report</code>.</li>
</ul>


---

## Solution

```java
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
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

