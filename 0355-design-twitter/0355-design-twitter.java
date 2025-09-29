class Twitter {
    private HashMap<Integer, HashSet<Integer>> followersMap;
    private HashMap<Integer, Integer> tweetIdMap;
    private HashMap<Integer, ArrayList<Integer>> userMap; 
    private HashMap<Integer, Integer> time;
    private int currTime;

    static class Pair {
        int node, time;
        public Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
        @Override
        public String toString() {
            return "(" + node + " " + time + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.node == node && current.time == time; 
        }
        @Override
        public int hashCode() {
            return Objects.hash(node, time);
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.time, first.time);
        }
    }

    public Twitter() {
        followersMap = new HashMap<>();
        tweetIdMap = new HashMap<>(); 
        userMap = new HashMap<>();
        time = new HashMap<>(); 
        currTime = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId))
            userMap.put(userId, new ArrayList<>());
        userMap.get(userId).add(tweetId);
        tweetIdMap.put(tweetId, userId);
        time.put(tweetId, currTime);
        if (!followersMap.containsKey(userId))
            followersMap.put(userId, new HashSet<>());
        followersMap.get(userId).add(userId);
        currTime++;
        System.out.println(followersMap);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        System.out.println(followersMap);
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> currFollowers = new HashSet<>();
        currFollowers = followersMap.getOrDefault(userId, new HashSet<>());
        currFollowers.add(userId);
        TreeSet<Pair> set = new TreeSet<>(new customSort());
        for (int ele : currFollowers) {
            ArrayList<Integer> tweetsMade = userMap.get(ele);
            if (tweetsMade == null) continue;
            for (int x : tweetsMade) {
                set.add(new Pair(x, time.get(x)));
            } 
        } 
        while (set.size() > 0 && res.size() < 10) {
            res.add(set.pollFirst().node);
        }
        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        if (!followersMap.containsKey(followerId))
            followersMap.put(followerId, new HashSet<>());
        followersMap.get(followerId).add(followeeId);  
        followersMap.get(followerId).add(followerId);   
    }
    
    public void unfollow(int followerId, int followeeId) {
        followersMap.get(followerId).remove(followeeId); 
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */