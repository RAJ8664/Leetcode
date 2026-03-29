class EventManager {
    private TreeSet<Pair> set;
    private HashMap<Integer, Integer> map;
    
    static class Pair {
        int event, priority;
        public Pair(int event, int priority) {
            this.event = event;
            this.priority = priority;
        }
        @Override
        public String toString() {
            return "(" + event + " " + priority + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.event == event && current.priority == priority;
        }
        @Override
        public int hashCode() {
            return Objects.hash(event, priority);
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(second.priority, first.priority);
            if (op1 != 0) 
                return op1;
            return Integer.compare(first.event, second.event);
        }
    }

    public EventManager(int[][] events) {
        set = new TreeSet<>(new customSort());
        map = new HashMap<>();
        for (int currEvent[]: events) {
            set.add(new Pair(currEvent[0], currEvent[1]));
            map.put(currEvent[0], currEvent[1]);
        }
    }
    
    public void updatePriority(int eventId, int newPriority) {
        Pair current = new  Pair(eventId, map.get(eventId));
        set.remove(new Pair(eventId, map.get(eventId)));
        map.put(eventId, newPriority);
        set.add(new Pair(eventId, newPriority));
    }
    
    public int pollHighest() {
        if (set.size() == 0) return -1;
        Pair current = set.pollFirst();
        map.remove(current.event);
        return current.event;
    }
}

/**
 * Your EventManager object will be instantiated and called as such:
 * EventManager obj = new EventManager(events);
 * obj.updatePriority(eventId,newPriority);
 * int param_2 = obj.pollHighest();
 */