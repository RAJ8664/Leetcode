# 2429. Design A Food Rating System

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/design-a-food-rating-system/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2429-design-a-food-rating-system){ .md-button }

---

<h2><a href="https://leetcode.com/problems/design-a-food-rating-system">2429. Design a Food Rating System</a></h2><h3>Medium</h3><hr><p>Design a food rating system that can do the following:</p>

<ul>
	<li><strong>Modify</strong> the rating of a food item listed in the system.</li>
	<li>Return the highest-rated food item for a type of cuisine in the system.</li>
</ul>

<p>Implement the <code>FoodRatings</code> class:</p>

<ul>
	<li><code>FoodRatings(String[] foods, String[] cuisines, int[] ratings)</code> Initializes the system. The food items are described by <code>foods</code>, <code>cuisines</code> and <code>ratings</code>, all of which have a length of <code>n</code>.

	<ul>
		<li><code>foods[i]</code> is the name of the <code>i<sup>th</sup></code> food,</li>
		<li><code>cuisines[i]</code> is the type of cuisine of the <code>i<sup>th</sup></code> food, and</li>
		<li><code>ratings[i]</code> is the initial rating of the <code>i<sup>th</sup></code> food.</li>
	</ul>
	</li>
	<li><code>void changeRating(String food, int newRating)</code> Changes the rating of the food item with the name <code>food</code>.</li>
	<li><code>String highestRated(String cuisine)</code> Returns the name of the food item that has the highest rating for the given type of <code>cuisine</code>. If there is a tie, return the item with the <strong>lexicographically smaller</strong> name.</li>
</ul>

<p>Note that a string <code>x</code> is lexicographically smaller than string <code>y</code> if <code>x</code> comes before <code>y</code> in dictionary order, that is, either <code>x</code> is a prefix of <code>y</code>, or if <code>i</code> is the first position such that <code>x[i] != y[i]</code>, then <code>x[i]</code> comes before <code>y[i]</code> in alphabetic order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FoodRatings&quot;, &quot;highestRated&quot;, &quot;highestRated&quot;, &quot;changeRating&quot;, &quot;highestRated&quot;, &quot;changeRating&quot;, &quot;highestRated&quot;]
[[[&quot;kimchi&quot;, &quot;miso&quot;, &quot;sushi&quot;, &quot;moussaka&quot;, &quot;ramen&quot;, &quot;bulgogi&quot;], [&quot;korean&quot;, &quot;japanese&quot;, &quot;japanese&quot;, &quot;greek&quot;, &quot;japanese&quot;, &quot;korean&quot;], [9, 12, 8, 15, 14, 7]], [&quot;korean&quot;], [&quot;japanese&quot;], [&quot;sushi&quot;, 16], [&quot;japanese&quot;], [&quot;ramen&quot;, 16], [&quot;japanese&quot;]]
<strong>Output</strong>
[null, &quot;kimchi&quot;, &quot;ramen&quot;, null, &quot;sushi&quot;, null, &quot;ramen&quot;]

<strong>Explanation</strong>
FoodRatings foodRatings = new FoodRatings([&quot;kimchi&quot;, &quot;miso&quot;, &quot;sushi&quot;, &quot;moussaka&quot;, &quot;ramen&quot;, &quot;bulgogi&quot;], [&quot;korean&quot;, &quot;japanese&quot;, &quot;japanese&quot;, &quot;greek&quot;, &quot;japanese&quot;, &quot;korean&quot;], [9, 12, 8, 15, 14, 7]);
foodRatings.highestRated(&quot;korean&quot;); // return &quot;kimchi&quot;
                                    // &quot;kimchi&quot; is the highest rated korean food with a rating of 9.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;ramen&quot;
                                      // &quot;ramen&quot; is the highest rated japanese food with a rating of 14.
foodRatings.changeRating(&quot;sushi&quot;, 16); // &quot;sushi&quot; now has a rating of 16.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;sushi&quot;
                                      // &quot;sushi&quot; is the highest rated japanese food with a rating of 16.
foodRatings.changeRating(&quot;ramen&quot;, 16); // &quot;ramen&quot; now has a rating of 16.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;ramen&quot;
                                      // Both &quot;sushi&quot; and &quot;ramen&quot; have a rating of 16.
                                      // However, &quot;ramen&quot; is lexicographically smaller than &quot;sushi&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n == foods.length == cuisines.length == ratings.length</code></li>
	<li><code>1 &lt;= foods[i].length, cuisines[i].length &lt;= 10</code></li>
	<li><code>foods[i]</code>, <code>cuisines[i]</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= ratings[i] &lt;= 10<sup>8</sup></code></li>
	<li>All the strings in <code>foods</code> are <strong>distinct</strong>.</li>
	<li><code>food</code> will be the name of a food item in the system across all calls to <code>changeRating</code>.</li>
	<li><code>cuisine</code> will be a type of cuisine of <strong>at least one</strong> food item in the system across all calls to <code>highestRated</code>.</li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>changeRating</code> and <code>highestRated</code>.</li>
</ul>


---

## Solution

```java
class FoodRatings {
    private HashMap<String, TreeSet<Pair>> map;
    private HashMap<String, ArrayList<String>> foodMap; // currentFood is present in which of the cousines;
    private HashMap<String, Integer> ratingMap; // Currently what is the rating of each food;
    static class Pair {
        String food;
        int rating;
        public Pair(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }
        @Override
        public String toString() {
            return "(" + food + " " + rating + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.food.equals(food) && current.rating == rating;
        }
        @Override
        public int hashCode() {
            return Objects.hash(food, rating);
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(second.rating, first.rating);
            if (op1 != 0)
                return op1;
            return first.food.compareTo(second.food);
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        map = new HashMap<>();
        foodMap = new HashMap<>();
        ratingMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String type = cuisines[i], food = foods[i];
            if (!map.containsKey(type)) 
                map.put(type, new TreeSet<Pair>(new customSort()));
            map.get(type).add(new Pair(foods[i], ratings[i]));
            if (!foodMap.containsKey(food))
                foodMap.put(food, new ArrayList<>());
            foodMap.get(food).add(cuisines[i]);
            ratingMap.put(foods[i], ratings[i]);
        } 
    }
    
    public void changeRating(String food, int newRating) {
        ArrayList<String> ids = new ArrayList<>();
        ids = foodMap.get(food);
        for (int i = 0; i < ids.size(); i++) {
            TreeSet<Pair> currCuines = new TreeSet<>();
            currCuines = map.get(ids.get(i));
            //update the food and its rating;
            int currRating = ratingMap.get(food);
            currCuines.remove(new Pair(food, currRating));
            currCuines.add(new Pair(food, newRating));
            map.put(ids.get(i), currCuines);
            ratingMap.put(food, newRating);
        } 
    }
    
    public String highestRated(String cuisine) {
        TreeSet<Pair> curr = map.get(cuisine);
        return curr.first().food; 
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

