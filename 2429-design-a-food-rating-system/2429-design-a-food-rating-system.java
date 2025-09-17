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